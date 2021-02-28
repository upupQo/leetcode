# coding:utf-8
#
# Author: BONFY<foreverbonfy@163.com>
# Github: https://github.com/bonfy
# Repo:   https://github.com/bonfy/leetcode
# Usage:  Leetcode solution downloader and auto generate readme
#
import requests
import os
import configparser
import json
import time
import datetime
import re
import sys
import html

from pathlib import Path
# from selenium import webdriver
from collections import namedtuple, OrderedDict

HOME = Path.cwd()
MAX_DIGIT_LEN = 4  # 1000+ PROBLEMS
SOLUTION_FOLDER_NAME = 'solutions'
SOLUTION_FOLDER = Path.joinpath(HOME, SOLUTION_FOLDER_NAME)
BASE_URL = 'https://leetcode.com'
# If you have proxy, change PROXIES below
PROXIES = None
HEADERS = {
    'Accept': '*/*',
    'Accept-Encoding': 'gzip,deflate,sdch',
    'Accept-Language': 'zh-CN,zh;q=0.8,gl;q=0.6,zh-TW;q=0.4',
    'Connection': 'keep-alive',
    'Content-Type': 'application/x-www-form-urlencoded',
    'Host': 'leetcode.com',
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36',
    # NOQA
}


# def get_config_from_file():
#     rst = dict(
#         language="java",
#     )
#     return rst


def rep_unicode_in_code(code):
    """
    Replace unicode to str in the code
    like '\u003D' to '='
    :param code: type str
    :return: type str
    """
    pattern = re.compile('(\\\\u[0-9a-zA-Z]{4})')
    m = pattern.findall(code)
    for item in set(m):
        code = code.replace(item, chr(int(item[2:], 16)))  # item[2:]去掉\u
    return code


def check_and_make_dir(dirname):
    p = Path(dirname)
    if not p.exists():
        p.mkdir(parents=True)


ProgLang = namedtuple('ProgLang', ['language', 'ext', 'annotation'])
ProgLangList = [
    ProgLang('cpp', 'cpp', '//'),
    ProgLang('java', 'java', '//'),
    ProgLang('python', 'py', '#'),
    ProgLang('python3', 'py', '#'),
    ProgLang('c', 'c', '//'),
    ProgLang('csharp', 'cs', '//'),
    ProgLang('javascript', 'js', '//'),
    ProgLang('ruby', 'rb', '#'),
    ProgLang('kotlin', 'kt', '//'),
    ProgLang('swift', 'swift', '//'),
    ProgLang('golang', 'go', '//'),
    ProgLang('scala', 'scala', '//'),
    ProgLang('rust', 'rs', '//'),
]
ProgLangDict = dict((item.language, item) for item in ProgLangList)
# CONFIG = get_config_from_file()


class QuizItem:
    """ QuizItem """
    base_url = BASE_URL

    def __init__(self, **data):
        self.__dict__.update(data)
        self.solutions = []

    def __str__(self):
        return '<Quiz: {question_id}-{question__title_slug}({difficulty})-{is_pass}>'.format(
            question_id=self.question_id,
            question__title_slug=self.question__title_slug,
            difficulty=self.difficulty,
            is_pass=self.is_pass,
        )

    def __repr__(self):
        return self.__str__()

    @property
    def json_object(self):
        addition_properties = [
            'is_pass', 'difficulty', 'is_lock', 'url', 'acceptance'
        ]
        dct = self.__dict__
        for prop in addition_properties:
            dct[prop] = getattr(self, prop)
        return dct

    @property
    def is_pass(self):
        return True if self.status == 'ac' else False

    @property
    def difficulty(self):
        difficulty = {1: "Easy", 2: "Medium", 3: "Hard"}
        return difficulty[self.level]

    @property
    def is_lock(self):
        return not self.is_favor and self.paid_only

    @property
    def url(self):
        return '{base_url}/problems/{question__title_slug}'.format(
            base_url=self.base_url,
            question__title_slug=self.question__title_slug,
        )

    @property
    def acceptance(self):
        return '%.1f%%' % (
                float(self.total_acs) * 100 / float(self.total_submitted)
        )


class Leetcode:

    def __init__(self):
        self.items = []
        self.submissions = []
        self.num_solved = 0
        self.num_total = 0
        self.num_lock = 0
        # change proglang to list
        # config set multi languages
        self.languages = [x.strip() for x in "java".split(',')]
        proglangs = [
            ProgLangDict[x.strip()] for x in "java".split(',')
        ]
        self.prolangdict = dict(zip(self.languages, proglangs))
        self.base_url = BASE_URL
        self.session = requests.Session()
        self.session.headers.update(HEADERS)
        self.session.proxies = PROXIES
        self.cookies = None

    def login(self):
        self.cookies = {
            "_ga":"GA1.2.266312652.1595998554",
            "__atuvc":"8|31",
            "__cfduid":"da7ae3427569a284555e8f4e80ce513211614484627",
            "_gid":"GA1.2.1628011054.1614484631",
            "csrftoken":"XJAdfupeoQiKC5DCfNkgK0BgnSXg1vPj5Z0v7IngLMYlfCUVVNDItkWFmhFpmwdc",
            "LEETCODE_SESSION":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfYXV0aF91c2VyX2lkIjoiNzAzOTQxIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiYWxsYXV0aC5hY2NvdW50LmF1dGhfYmFja2VuZHMuQXV0aGVudGljYXRpb25CYWNrZW5kIiwiX2F1dGhfdXNlcl9oYXNoIjoiZDFlZjc4MTY4YmFjYTdiMmJkZmY5Y2YzMjM0ZGI4MjE0ZjIyMGMyZSIsImlkIjo3MDM5NDEsImVtYWlsIjoiMjAyNDE0OTIxMkBxcS5jb20iLCJ1c2VybmFtZSI6ImhmdmoiLCJ1c2VyX3NsdWciOiJoZnZqIiwiYXZhdGFyIjoiaHR0cHM6Ly9hc3NldHMubGVldGNvZGUuY29tL3VzZXJzL21zbGwvYXZhdGFyXzE1MTkzMDc2MDAucG5nIiwicmVmcmVzaGVkX2F0IjoxNjE0NDg0NjQyLCJpcCI6IjIwMi43Ni4yNDcuMTQ2IiwiaWRlbnRpdHkiOiI1MzA4YTc1NmU3YzQ5ODY5YjAzOTdhNDVkN2Y2NjZlZCIsInNlc3Npb25faWQiOjY4Mjk2Mzh9.hUHEAHnxu5-hxszo8cKD_rQiCvmSHfbWfRowPecGNVU",
            "__cf_bm":"46469bc5814e862616050fb9f648c3fd97047cb6-1614485896-1800-AeVfze5t8zaPDrPOU21ebmPUlFAe31pUo1jprTht5B7kEkjEizJBVFKHH5gs96W0KbryygnfSk8zkgSbY/AEfeU="
        }
        self.session.cookies.update(self.cookies)

    def load_items_from_api(self):
        """ load items from api"""
        api_url = self.base_url + '/api/problems/algorithms/'  # NOQA
        r = self.session.get(api_url, proxies=PROXIES)
        assert r.status_code == 200
        rst = json.loads(r.text)
        # make sure your user_name is not None
        # thus the stat_status_pairs is real
        if not rst['user_name']:
            raise Exception("Something wrong with your personal info.\n")

        self.items = []  # destroy first ; for sake maybe needn't
        self.num_solved = rst['num_solved']
        self.num_total = rst['num_total']
        self.items = list(self._generate_items_from_api(rst))
        self.num_lock = len([i for i in self.items if i.is_lock])
        self.items.reverse()

    def load(self):
        """
        load: all in one

        login -> load api -> load submissions -> solutions to items
        return `all in one items`
        """
        # if cookie is valid, get api_url twice
        # TODO: here can optimize
        if not self.is_login:
            self.login()
        self.load_items_from_api()
        self.load_submissions()
        self.load_solutions_to_items()

    def _generate_items_from_api(self, json_data):
        stat_status_pairs = json_data['stat_status_pairs']
        for quiz in stat_status_pairs:
            if quiz['stat']['question__hide']:
                continue

            data = {}
            data['question__title_slug'] = quiz['stat']['question__title_slug']
            data['question__title'] = quiz['stat']['question__title']
            data['question__article__slug'] = quiz['stat'][
                'question__article__slug'
            ]
            # data['is_paid'] = json_data['is_paid']
            data['paid_only'] = quiz['paid_only']
            data['level'] = quiz['difficulty']['level']
            data['is_favor'] = quiz['is_favor']
            data['total_acs'] = quiz['stat']['total_acs']
            data['total_submitted'] = quiz['stat']['total_submitted']
            data['question_id'] = quiz['stat']['question_id']
            data['status'] = quiz['status']
            item = QuizItem(**data)
            yield item

    @property
    def is_login(self):
        return 1!=1

    def load_submissions(self):
        """ load all submissions from leetcode """
        # set limit a big num
        print('API load submissions request 2 seconds per request')
        print('Please wait ...')
        limit = 20
        offset = 0
        last_key = ''
        while True:
            print('try to load submissions from ', offset, ' to ', offset + limit)
            submissions_url = '{}/api/submissions/?format=json&limit={}&offset={}&last_key={}'.format(
                self.base_url, limit, offset, last_key
            )

            resp = self.session.get(submissions_url, proxies=PROXIES)
            # print(submissions_url, ':', resp.status_code)
            assert resp.status_code == 200
            data = resp.json()
            if 'has_next' not in data.keys():
                raise Exception('Get submissions wrong, Check network\n')

            self.submissions += data['submissions_dump']
            if data['has_next']:
                offset += limit
                last_key = data['last_key']
                # print('last_key:', last_key)
                time.sleep(2.5)
            else:
                break

    def load_solutions_to_items(self):
        """
        load all solutions to items

        combine submission's `runtime` `title` `lang` `submission_url` to items
        """
        titles = [i.question__title for i in self.items]
        itemdict = OrderedDict(zip(titles, self.items))

        def make_sub(sub):
            return dict(
                runtime=int(sub['runtime'][:-3]),
                title=sub['title'],
                lang=sub['lang'],
                submission_url=self.base_url + sub['url'],
            )

        ac_subs = [
            make_sub(sub)
            for sub in self.submissions
            if sub['status_display'] == 'Accepted'
        ]

        def remain_shortesttime_submissions(submissions):
            submissions_dict = {}
            for item in submissions:
                k = '{}-{}'.format(item['lang'], item['title'])
                if k not in submissions_dict.keys():
                    submissions_dict[k] = item
                else:
                    old = submissions_dict[k]
                    if item['runtime'] < old['runtime']:
                        submissions_dict[k] = item
            return list(submissions_dict.values())

        shortest_subs = remain_shortesttime_submissions(ac_subs)
        for solution in shortest_subs:
            title = solution['title']
            if title in itemdict.keys():
                itemdict[title].solutions.append(solution)

    def _get_code_by_solution(self, solution):
        """
        get code by solution

        solution: type dict
        """
        solution_url = solution['submission_url']
        print(solution_url)
        r = self.session.get(solution_url, proxies=PROXIES)
        assert r.status_code == 200
        pattern = re.compile(
            r'<meta name=\"description\" content=\"(?P<question>.*)\" />\n    \n    <meta property=\"og:image\"',
            re.S,
        )
        m1 = pattern.search(r.text)
        question = m1.groupdict()['question'] if m1 else None
        if not question:
            raise Exception(
                'Can not find question descript in question:{title}'.format(
                    title=solution['title']
                )
            )

        # html.unescape to remove &quot; &#39;
        question = html.unescape(question)
        pattern = re.compile(
            r'submissionCode: \'(?P<code>.*)\',\n  editCodeUrl', re.S
        )
        m1 = pattern.search(r.text)
        code = m1.groupdict()['code'] if m1 else None
        if not code:
            raise Exception(
                'Can not find solution code in question:{title}'.format(
                    title=solution['title']
                )
            )

        code = rep_unicode_in_code(code)
        return question, code

    def _get_code_with_anno(self, solution):
        question, code = self._get_code_by_solution(solution)
        language = solution['lang']
        # generate question with anno
        lines = []
        for line in question.split('\n'):
            if line.strip() == '':
                lines.append(self.prolangdict[language].annotation)
            else:
                lines.append(
                    '{anno} {line}'.format(
                        anno=self.prolangdict[language].annotation,
                        line=html.unescape(line),
                    )
                )
        quote_question = '\n'.join(lines)
        # generate content
        content = '# -*- coding:utf-8 -*-' + '\n' * 3 if language == 'python' else ''
        content += quote_question
        content += '\n' * 3
        content += code
        content += '\n'
        return content

    def _download_code_by_quiz(self, quiz):
        """
        Download code by quiz
        quiz: type QuizItem
        """
        qid = quiz.question_id
        qtitle = quiz.question__title_slug
        slts = list(
            filter(lambda i: i['lang'] in self.languages, quiz.solutions)
        )
        if not slts:
            print(
                'No solution with the set languages in question:{}-{}'.format(
                    qid, qtitle
                )
            )
            return
        # 中划线改为下划线。使符合Java命名规范
        qname = 'Q{id}_{title}'.format(id=qid,title=qtitle.replace('-','_'))
        print('begin download ' + qname)
        path = Path.joinpath(SOLUTION_FOLDER, qname)
        check_and_make_dir(path)
        for slt in slts:
            fname = '{title}.{ext}'.format(
                title=qtitle.replace('-','_'), ext=self.prolangdict[slt['lang']].ext
            )
            filename = Path.joinpath(path, fname)
            content = self._get_code_with_anno(slt)
            import codecs

            with codecs.open(filename, 'w', 'utf-8') as f:
                print('write to file ->', fname)
                f.write(content)

    def _find_item_by_quiz_id(self, qid):
        """
        find the item by quiz id
        """
        lst = list(filter(lambda x: x.question_id == qid, self.items))
        if len(lst) == 1:
            return lst[0]

        print('No exits quiz id:', qid)

    def download_by_id(self, qid):
        quiz = self._find_item_by_quiz_id(qid)
        if quiz:
            self._download_code_by_quiz(quiz)

    def download(self):
        """ download all solutions with single thread """
        ac_items = [i for i in self.items if i.is_pass]
        for quiz in ac_items:
            time.sleep(1)
            self._download_code_by_quiz(quiz)

    def download_with_thread_pool(self):
        """ download all solutions with multi thread """
        ac_items = [i for i in self.items if i.is_pass]
        from concurrent.futures import ThreadPoolExecutor

        pool = ThreadPoolExecutor(max_workers=4)
        for quiz in ac_items:
            pool.submit(self._download_code_by_quiz, quiz)
        pool.shutdown(wait=True)

    def write_readme(self):
        """Write Readme to current folder"""
        languages_readme = ','.join([x.capitalize() for x in self.languages])
        md = '''
I have solved **{num_solved}** problems
| # | Title | Source Code | Difficulty | Last submission url |
|:---:|:---:|:---:|:---:|:---:|'''.format(
            num_solved=self.num_solved
        )
        for item in self.items:
            # md中只记录AC的题目
            if item.status != 'ac':
                continue
            else:
                md += '\n'
                if item.solutions:
                    dirname = '{folder}/{id}_{title}'.format(
                        folder=SOLUTION_FOLDER_NAME,
                        id=str(item.question_id),
                        title=item.question__title_slug.replace('-','_'),
                    )
                    language = ''
                    language_lst = [
                        i['lang']
                        for i in item.solutions
                        if i['lang'] in self.languages
                    ]
                    while language_lst:
                        lan = language_lst.pop()
                        language += '[{language}](/https://github.com/MsLL/leetcode/blob/master/{dirname}/{title}.{ext})'.format(
                            language=lan.capitalize(),
                            dirname=dirname,
                            title=item.question__title_slug.replace('-','_'),
                            ext=self.prolangdict[lan].ext,
                        )
                        language += ' '
                else:
                    language = ''
            language = language.strip()
            md += '|{id}|{title}|{language}|{difficulty}|{lastSubmissionUrl}|\n'.format(
                id=item.question_id,
                title=item.question__title_slug,
                language=language,
                difficulty=item.difficulty,
                lastSubmissionUrl=item.solutions[0]['submission_url']
            )
        with open('README.md', 'w') as f:
            f.write(md)

    def push_to_github(self):
        strdate = datetime.datetime.now().strftime('%Y-%m-%d')
        cmd_git_add = 'git add .'
        cmd_git_commit = 'git commit -m "update at {date}"'.format(
            date=strdate
        )
        cmd_git_push = 'git push -u origin master'
        os.system(cmd_git_add)
        os.system(cmd_git_commit)
        os.system(cmd_git_push)


def do_job(leetcode):
    leetcode.load()
    print('Leetcode load self info')
    if len(sys.argv) == 1:
        # simple download
        # leetcode.dowload()
        # we use multi thread
        print('download all leetcode solutions')
        # leetcode.download_with_thread_pool()
        leetcode.download()
    else:
        for qid in sys.argv[1:]:
            print('begin leetcode by id: {id}'.format(id=qid))
            leetcode.download_by_id(int(qid))
    print('Leetcode finish dowload')
    leetcode.write_readme()
    print('Leetcode finish write readme')


if __name__ == '__main__':
    leetcode = Leetcode()
    do_job(leetcode)

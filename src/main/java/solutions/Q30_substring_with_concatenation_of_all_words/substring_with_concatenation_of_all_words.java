// You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.
//
// You can return the answer in any order.
//
//  
// Example 1:
//
//
// Input: s = "barfoothefoobarman", words = ["foo","bar"]
// Output: [0,9]
// Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
// The output order does not matter, returning [9,0] is fine too.
//
//
// Example 2:
//
//
// Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
// Output: []
//
//
// Example 3:
//
//
// Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
// Output: [6,9,12]
//
//
//  
// Constraints:
//
//
// 	1 <= s.length <= 104
// 	s consists of lower-case English letters.
// 	1 <= words.length <= 5000
// 	1 <= words[i].length <= 30
// 	words[i] consists of lower-case English letters.
//
//


class Solution {
public List<Integer> findSubstring(String s, String[] words) {
    	Map<String, Integer> fremap=new HashMap<String,Integer>();//words中单词的统计频率,用于对比
    	Map<String, Integer> foundmap=new HashMap<String,Integer>();//[start,j)区间中的单词频率,notice.每次跟新大头时要clean
    	int wl=words[0].length();//单个单词长度key
    	int cTotal=words.length;//words总单词数
    	int cCurent;//[start,j)区间总单词数目
    	List<Integer> res=new ArrayList<Integer>();
    	//1.初始化fremap
    	for(int i=0;i<words.length;i++){
    		if(fremap.containsKey(words[i])){
    			fremap.put(words[i], fremap.get(words[i])+1);
    		}else{
    			fremap.put(words[i], 1);
    		}
    	}
    	//2.枚举，维护区间
    	for(int i=0;i<wl;i++){//key,大的枚举头[0,wl-1]只需要wl次,后续枚举头均是这几种情况的子过程,反向证明
    		foundmap.clear();
    		cCurent=0;
    		//key,[start,j)维护一个一个有效区间,j为下一个待验证单词的首字符位置
    		int start=i;
    		int j=start;
    		String str;
    		while(j+wl<=s.length()){
    			str=s.substring(j, j+wl);
    			if(!fremap.containsKey(str)){//不是words中的单词,start需要彻底换了
    				foundmap.clear();
                    cCurent=0;//notice
    				start=j+wl;//start更新为j+wl,含[j,j+wl)这个非words中的单词不可能构成解
    			}else if(foundmap.containsKey(str) && foundmap.get(str)==fremap.get(str)){//已经出现了总次数，这个是多余的,过滤掉[start,preIndex+wl)
    				String temp;
    				while(start<j){//过滤掉[start,preIndex+wl)
    					temp=s.substring(start, start+wl);
    					foundmap.put(temp, foundmap.get(temp)-1);
    					cCurent--;//有效单词数减1
    					start+=wl;
    					if(temp.equals(str)){
                            cCurent++;//notice,过滤一些单词，再把当前单词加进去
        					foundmap.put(str, foundmap.get(str)+1);
    						break;
    					}
    				}
    			}else{//得以延伸
    				if(!foundmap.containsKey(str)){
    					foundmap.put(str, 1);
    				}else{
    					foundmap.put(str, foundmap.get(str)+1);
    				}
    				cCurent++;
    				if(cCurent==cTotal){//已经找到了cTotal个有效单词
    					res.add(start);
    					String head=s.substring(start, start+wl);
    					foundmap.put(head, foundmap.get(head)-1);
    					cCurent--;
    					start+=wl;//把头移向下一个单词首字符
    				}
    			}
    			j+=wl;//当前单词已经验证完,指向下一个单词首字符
    		}
    	}
    	return res;
    }
}


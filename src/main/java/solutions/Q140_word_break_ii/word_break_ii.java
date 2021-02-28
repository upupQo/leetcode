// Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
//
// Note:
//
//
// 	The same word in the dictionary may be reused multiple times in the segmentation.
// 	You may assume the dictionary does not contain duplicate words.
//
//
// Example 1:
//
//
// Input:
// s = "catsanddog"
// wordDict = ["cat", "cats", "and", "sand", "dog"]
// Output:
// [
//   "cats and dog",
//   "cat sand dog"
// ]
//
//
// Example 2:
//
//
// Input:
// s = "pineapplepenapple"
// wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
// Output:
// [
//   "pine apple pen apple",
//   "pineapple pen apple",
//   "pine applepen apple"
// ]
// Explanation: Note that you are allowed to reuse a dictionary word.
//
//
// Example 3:
//
//
// Input:
// s = "catsandog"
// wordDict = ["cats", "dog", "sand", "and", "cat"]
// Output:
// []
//


class Solution {
    	Map<String,List<String>> map;//用map缓存子串str可以由wordDict组成的所有形式,null说明不能组成
        public List<String> wordBreak(String s, List<String> wordDict) {
            this.map=new HashMap<String,List<String>>();
        	wordBreak(0, s, wordDict);
            if(map.get(s)!=null){//可以分割
                return map.get(s);
            }else{
                return new ArrayList<String>();
            }
        }
        private List<String> wordBreak(int start,String s,List<String> wordDict){
        	if(start==s.length()){
        		return new ArrayList<String>();//notice
        	}
        	if(map.containsKey(s.substring(start,s.length()))){
        		return map.get(s.substring(start,s.length()));
        	}
        	List<String> list=new ArrayList<String>();
        	for(int i=start;i<s.length();i++){
        		if(wordDict.contains(s.substring(start, i+1))){//切出一个单词[start,i]
        			List<String> temp=wordBreak(i+1, s, wordDict);
        			if(temp==null){//后面没法成功break
        				continue;
        			}
        			list.addAll(combine(s.substring(start, i+1),temp));
        		}
        	}
        	if(list.size()==0){//s[start,n-1]不能被break为,在map里面放一个null标识
        		map.put(s.substring(start), null);
        	}else{
        		map.put(s.substring(start), list);
        	}
        	return list.size()==0?null:list;//notice
        }
        private List<String>combine(String s,List<String> list){
        	List<String> res=new ArrayList<String>();
        	if(list.size()==0){
        		res.add(s);
        	}else{
        		for(int i=0;i<list.size();i++){
        			res.add(s+" "+list.get(i));
        		}
        	}
        	return res;
        }
    }

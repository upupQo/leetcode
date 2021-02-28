// Given an array of strings strs, group the anagrams together. You can return the answer in any order.
//
// An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
//
//  
// Example 1:
// Input: strs = ["eat","tea","tan","ate","nat","bat"]
// Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
// Example 2:
// Input: strs = [""]
// Output: [[""]]
// Example 3:
// Input: strs = ["a"]
// Output: [["a"]]
//
//  
// Constraints:
//
//
// 	1 <= strs.length <= 104
// 	0 <= strs[i].length <= 100
// 	strs[i] consists of lower-case English letters.
//
//


class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map=new HashMap<String,List<String>>();
        int[] count;//记录每个单词出现的次数
        String str;
        StringBuilder sb;
        for(int i=0;i<strs.length;i++){
        	str=strs[i];
        	//1.统计词频
        	count=new int[26];
        	for(int j=0;j<str.length();j++){
        		count[str.charAt(j)-'a']++;
        	}
        	//2.词频变标记
        	sb=new StringBuilder();
        	for(int j=0;j<26;j++){
        		sb.append((char)('a'+j));
        		sb.append(count[j]+"");//5-->"5"
        	}
        	//3.
        	if(map.containsKey(sb.toString())){
        		map.get(sb.toString()).add(str);
        	}else{
        		List<String> list=new ArrayList<String>();
        		list.add(str);
        		map.put(sb.toString(), list);
        	}
        }
        List<List<String>>  res=new ArrayList<List<String>>();
        for(String key : map.keySet()){
        	res.add(map.get(key));
        }
        return res;
    }
}

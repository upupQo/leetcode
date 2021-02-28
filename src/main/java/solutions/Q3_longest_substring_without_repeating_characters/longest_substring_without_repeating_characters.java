// Given a string s, find the length of the longest substring without repeating characters.
//
//  
// Example 1:
//
//
// Input: s = "abcabcbb"
// Output: 3
// Explanation: The answer is "abc", with the length of 3.
//
//
// Example 2:
//
//
// Input: s = "bbbbb"
// Output: 1
// Explanation: The answer is "b", with the length of 1.
//
//
// Example 3:
//
//
// Input: s = "pwwkew"
// Output: 3
// Explanation: The answer is "wke", with the length of 3.
// Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
//
//
// Example 4:
//
//
// Input: s = ""
// Output: 0
//
//
//  
// Constraints:
//
//
// 	0 <= s.length <= 5 * 104
// 	s consists of English letters, digits, symbols and spaces.
//
//


class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0){
        	return 0;
        }
        int[] map=new int[256];//拓展的ascii为256个字符,map[i]记录ascii为i的字符上一次出现的位置(即最近出现的位置)，尚未出现的标记为-1
        for(int i=0;i<256;i++){
        	map[i]=-1;
        }
        int res=1;//记录无重复字符最长子串长度--至少为1
        int i=0;//枚举子串开头
        int j=1;
        while(i<s.length()){
        	map[s.charAt(i)]=i;//notice.更新最近出现的位置
        	while(j<s.length() && (map[s.charAt(j)]<i || map[s.charAt(j)]==j)){//keyPoint,最近出现在i之前或最近出现位置在当前j位置
        		map[s.charAt(j)]=j;//更新最近出现的位置
        		j++; 
        	}
        	res=Math.max(res,j-i);//length(arr[i,j-1])
        	if(j<s.length()){
        		i=map[s.charAt(j)]+1;//枚举头跳跃到map[s.charAt(j)]+1位置。设k=map[s.charAt(j)],此时必有
        		map[s.charAt(j)]=j;
        	}else{
        		//j==s.length(),说明以arr[i]为首字符的无重复子串为arr[i,n-1],后面的过程无需在枚举
        		break;
        	}
        }
        return res;
    }
}

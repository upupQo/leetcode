// Given a string s, you can convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
//
//  
// Example 1:
// Input: s = "aacecaaa"
// Output: "aaacecaaa"
// Example 2:
// Input: s = "abcd"
// Output: "dcbabcd"
//
//  
// Constraints:
//
//
// 	0 <= s.length <= 5 * 104
// 	s consists of lowercase English letters only.
//
//


class Solution {
    public String shortestPalindrome(String s) {
        if(s.length()==0){
        	return s;
        }
        StringBuilder sb=new StringBuilder(s);
        sb.append('#');//加一个标识防止s+reverse(s)得到的最长公共前后缀长度超过原串
        sb.append(new StringBuilder(s).reverse().toString());
        sb.append('!');//在最后任意加一个字符(遵循kmp的next数组的语义,求目标最长公共前后缀),不与标志字符‘#’相同即可。
        return new StringBuilder(s.substring(getNext(sb.toString())[sb.length()-1])).reverse().append(s).toString();
    }
    /**
     * kmp算法.通过模式串得失配next数组--‘next[i],在i位置发生失配’,pattern[0,i-1]的最长公共前后缀'长度',又下一个字符的索引值==长度
     * next[i],pattern[0,i-1]的最长公共前后缀的长度,notice.length((前缀|后缀))<=length(str)-1,即前缀后缀的概念不含字符串本身
     */
    private int[] getNext(String pattern){
    	int[] next=new int[pattern.length()];
    	next[0]=-1;
    	int pre;//待验证字符索引,关键变量
    	for(int i=1;i<pattern.length();i++){
    		pre=next[i-1];
    		while(pre!=-1 && pattern.charAt(i-1)!=pattern.charAt(pre)){//比较pattern[i-1]与pattern[pre]
    			pre=next[pre];//kmp核心
    		}
    		if(pre==-1){
    			next[i]=0;
    		}else{
    			next[i]=pre+1;
    		}
    	}
    	return next;
    }
}

// Implement strStr().
//
// Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
//
// Clarification:
//
// What should we return when needle is an empty string? This is a great question to ask during an interview.
//
// For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
//
//  
// Example 1:
// Input: haystack = "hello", needle = "ll"
// Output: 2
// Example 2:
// Input: haystack = "aaaaa", needle = "bba"
// Output: -1
// Example 3:
// Input: haystack = "", needle = ""
// Output: 0
//
//  
// Constraints:
//
//
// 	0 <= haystack.length, needle.length <= 5 * 104
// 	haystack and needle consist of only lower-case English characters.
//
//


class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length()>haystack.length()){
        	return -1;
        }
        if(needle.length()==0){
            return 0;
        }
        int[] next=getNext(needle);
        int i=0,j=0;
        while(j<needle.length() && i<haystack.length()){
        	if(haystack.charAt(i)==needle.charAt(j)){
        		i++;
        		j++;
        	}else{
        		if(j!=0){//next[j]语义:在j位置发生失配,pattern[0,j-1]的最长公共前后缀长度--》下一个待考虑字符索引。当j在0位置时，没有这个概念
            		j=next[j];
        		}else{
                    i++;//j==0,notice与i位置无法匹配，i主动向后走
                }
        	}
        }
        if(j<needle.length()){
        	return -1;
        }
        return i-j;
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

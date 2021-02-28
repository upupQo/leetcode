// Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where: 
//
//
// 	'.' Matches any single character.​​​​
// 	'*' Matches zero or more of the preceding element.
//
//
// The matching should cover the entire input string (not partial).
//
//  
// Example 1:
//
//
// Input: s = "aa", p = "a"
// Output: false
// Explanation: "a" does not match the entire string "aa".
//
//
// Example 2:
//
//
// Input: s = "aa", p = "a*"
// Output: true
// Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
//
//
// Example 3:
//
//
// Input: s = "ab", p = ".*"
// Output: true
// Explanation: ".*" means "zero or more (*) of any character (.)".
//
//
// Example 4:
//
//
// Input: s = "aab", p = "c*a*b"
// Output: true
// Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
//
//
// Example 5:
//
//
// Input: s = "mississippi", p = "mis*is*p*."
// Output: false
//
//
//  
// Constraints:
//
//
// 	0 <= s.length <= 20
// 	0 <= p.length <= 30
// 	s contains only lowercase English letters.
// 	p contains only lowercase English letters, '.', and '*'.
// 	It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
//
//


class Solution {
    public boolean isMatch(String s, String p) {
        char[] strs=s.toCharArray();
        char[] strp=p.toCharArray();
        //dp[i][j]表示模式串p[0,j]能否完全匹配文本串s[0,i]
        //1若s[i]==p[j] || p[j]=='.'则dp[i][j]=dp[i-1][j-1];
        //2若p[j]=='*',if(s[i-1]!=p[j] && s[i-1]!='.')则dp[i][j]=dp[i][j-2],否则dp[i][j]=dp[i][j-2](0次)  || dp[i-1][j-2](一次) || dp[i-1][j](多次)
        boolean[][] dp=new boolean[s.length()+1][p.length()+1];//假设在s和p的开头各加一个空字符' ',那么他们的长度为s.length+1,p.length+1。Java数组默认被初始化为false
        dp[0][0]=true;
        //1.初始化第一行
        for(int j=1;j<dp[0].length;j++){
        	if(strp[j-1]=='*' && j>1){
        		dp[0][j]=dp[0][j-2];
        	}
        }
        //2.逐行填,从左往右填
        for(int i=1;i<dp.length;i++){
        	for(int j=1;j<dp[0].length;j++){//除dp[0][0]外,dp[i][0]为fasle:空的模式串肯定无法匹配一个非空的文本串
        		if(strp[j-1]==strs[i-1] || strp[j-1]=='.'){//j位置字符可以直接匹配i位置字符
        			dp[i][j]=dp[i-1][j-1];
        		}else if(strp[j-1]=='*' && j>1){//j位置字符为*,向前看一个字符
        			if(strp[j-2]==strs[i-1] || strp[j-2]=='.'){//在当前字符为*的情况下,前一个字符可以匹配i位置字符
        				dp[i][j]=dp[i][j-2] || dp[i-1][j-2] || dp[i-1][j];//不用|用一次|用多次(>=1)
        			}else{//在当前字符为*的情况下,前一个字符不能匹配i位置字符
        				dp[i][j]=dp[i][j-2];
        			}
        		}
        		//否则仍是默认值false;
        	}
        }
        return dp[strs.length][strp.length];
    }
}

// Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
//
//
// 	'?' Matches any single character.
// 	'*' Matches any sequence of characters (including the empty sequence).
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
// Input: s = "aa", p = "*"
// Output: true
// Explanation: '*' matches any sequence.
//
//
// Example 3:
//
//
// Input: s = "cb", p = "?a"
// Output: false
// Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
//
//
// Example 4:
//
//
// Input: s = "adceb", p = "*a*b"
// Output: true
// Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
//
//
// Example 5:
//
//
// Input: s = "acdcb", p = "a*c?b"
// Output: false
//
//
//  
// Constraints:
//
//
// 	0 <= s.length, p.length <= 2000
// 	s contains only lowercase English letters.
// 	p contains only lowercase English letters, '?' or '*'.
//
//


class Solution {
    public boolean isMatch(String s, String p) {
        char[] strs=s.toCharArray();
        char[] strp=p.toCharArray();
        boolean[][] dp=new boolean[strs.length+1][strp.length+1];//假设各自在前面加一个空字符，空间过程注意dp数组与字符位置的转变，dp[i][j]表示strs[0,i)与strp[0,j)能否匹配
        dp[0][0]=true;//s,p均为空串""
        //1.初始化首行,p[0,j)能否匹配空串""
        for(int j=1;j<dp[0].length;j++){
        	if(strp[j-1]=='*'){//用*号匹配空串""
        		dp[0][j]=dp[0][j-1];
        	}
        	//默认为false
        }
        //2.逐行填,从左往右填
        for(int i=1;i<dp.length;i++){
        	for(int j=1;j<dp[0].length;j++){//除dp[0][0]外，dp[i][0]均为默认的false,因为空串无法匹配非空文本串strs[0,i),i>=1
        		if(strp[j-1]==strs[i-1] || strp[j-1]=='?'){//j-1位置字符可以直接匹配i-1位置字符
        			dp[i][j]=dp[i-1][j-1];
        		}else if(strp[j-1]=='*'){//j-1位置为*
        			dp[i][j]=dp[i][j-1] || dp[i-1][j-1] || dp[i-1][j];//*充当0个字符|一个字符|多个字符>=1
        		}
        	}
        }
        return dp[strs.length][strp.length];
    }
}

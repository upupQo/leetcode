// Given two strings s and t, return the number of distinct subsequences of s which equals t.
//
// A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).
//
// It is guaranteed the answer fits on a 32-bit signed integer.
//
//  
// Example 1:
//
//
// Input: s = "rabbbit", t = "rabbit"
// Output: 3
// Explanation:
// As shown below, there are 3 ways you can generate "rabbit" from S.
// rabbbit
// rabbbit
// rabbbit
//
//
// Example 2:
//
//
// Input: s = "babgbag", t = "bag"
// Output: 5
// Explanation:
// As shown below, there are 5 ways you can generate "bag" from S.
// babgbag
// babgbag
// babgbag
// babgbag
// babgbag
//
//  
// Constraints:
//
//
// 	0 <= s.length, t.length <= 1000
// 	s and t consist of English letters.
//
//


class Solution {
    //115.在字符串S[0,n-1]中equalsT字符串[0,m-1]的子序列有多少少个
    public int numDistinct(String s, String t) {
        //dp[i][j]:在字符串S[0,i]中equals字符串[0,j]的子序列有多少个
    	//if(s[i]==t[j]) dp[i][j]=dp[i-1][j-1]+dp[i-1][j];//抵消、不抵消
    	//if(s[i]!=t[j]) dp[i][j]=dp[i-1][j]
    	if(s.length()<t.length()){
    		return 0;
    	}
    	int m=s.length();
    	int n=t.length();
    	int[][] dp=new int[m][n];
    	dp[0][0]=s.charAt(0)==t.charAt(0)?1:0;
    	for(int j=1;j<n;j++){
    		dp[0][j]=0;
    	}
    	for(int i=1;i<m;i++){
    		if(s.charAt(i)==t.charAt(0)){
    			dp[i][0]=dp[i-1][0]+1;
    		}else{
    			dp[i][0]=dp[i-1][0];
    		}
    	}
    	for(int i=1;i<m;i++){
    		for(int j=1;j<n;j++){
    			if(s.charAt(i)==t.charAt(j)){
    				dp[i][j]=dp[i-1][j-1]+dp[i-1][j];//key
    			}else{
    				dp[i][j]=dp[i-1][j];
    			}
    		}
    	}
    	return dp[m-1][n-1];
    }
}

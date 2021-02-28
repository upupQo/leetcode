// Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
//
// An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
//
//
// 	s = s1 + s2 + ... + sn
// 	t = t1 + t2 + ... + tm
// 	|n - m| <= 1
// 	The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
//
//
// Note: a + b is the concatenation of strings a and b.
//
//  
// Example 1:
//
//
// Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
// Output: true
//
//
// Example 2:
//
//
// Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
// Output: false
//
//
// Example 3:
//
//
// Input: s1 = "", s2 = "", s3 = ""
// Output: true
//
//
//  
// Constraints:
//
//
// 	0 <= s1.length, s2.length <= 100
// 	0 <= s3.length <= 200
// 	s1, s2, and s3 consist of lower-case English letters.
//
//


class Solution {
    //dp[i][j]:s1[0,i)与s2[0,j)是否可以交叉构成s3[0,i+j)
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()){
        	return false;
        }
        int m=s1.length()+1;
        int n=s2.length()+1;
        boolean[][] dp=new boolean[m][n];
        //1.
        dp[0][0]=true;//""和"" 构成的仍然是"",符合定义 
        int j=1;
        while(j<n && s2.charAt(j-1)==s3.charAt(j-1)){
        	dp[0][j++]=true;
        }
        while(j<n){
        	dp[0][j++]=false;
        }
        int i=1;
        while(i<m && s1.charAt(i-1)==s3.charAt(i-1)){
        	dp[i++][0]=true;
        }
        while(i<m){
        	dp[i++][0]=false;
        }
        for(i=1;i<m;i++){
        	for(j=1;j<n;j++){
        		if(s1.charAt(i-1)==s3.charAt(i+j-1) && s2.charAt(j-1)==s3.charAt(i+j-1)){
        			dp[i][j]=dp[i-1][j] || dp[i][j-1];
        		}else if(s3.charAt(i+j-1)==s1.charAt(i-1)){
        			dp[i][j]=dp[i-1][j];
        		}else if(s3.charAt(i+j-1)==s2.charAt(j-1)){
        			dp[i][j]=dp[i][j-1];
        		}else{
        			dp[i][j]=false;
        		}
        	}
        }
        return dp[m-1][n-1];
    }
}

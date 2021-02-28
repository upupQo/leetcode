//
// Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.
//
//
// Example 1:
//
// Input: "sea", "eat"
// Output: 2
// Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
//
//
//
// Note:
//
// The length of given words won't exceed 500.
// Characters in given words can only be lower-case letters.
//
//


class Solution {
    //583.Delete Operation for Two Strings.给定两个单词,只允许删除操作,返回最少操作步数,使两个字符串相同。字符均为小写字母
    //与72相比，没有稳定的目标串.实质就是最长公共子序列.dp[i][j]代表w1[0,i]与w2[0,j]的最长公共子序列
    //若w1[i]==w[j],dp[i][j]=dp[i-1][j-1]+1,否则dp[i][j]=max(dp[i-1][j],dp[i][j-1])
    public int minDistance(String word1, String word2) {
        if(word1.length()==0){
        	return word2.length();
        }
        if(word2.length()==0){
        	return word1.length();
        }
        int m=word1.length();
        int n=word2.length();
        int[][] dp=new int[m][n];
        //初始化第一行、第一列
        int j=0;
        while(j<n && word1.charAt(0)!=word2.charAt(j)){//在未出现与w1[0]相同的字符之前,w2[0,j]与w1[0]的最长公共子序列为0,一旦出现，那么后续w2[0,j]与w1[0]的最长公共子序列为1
        	dp[0][j++]=0;
        }
        while(j<n){
        	dp[0][j++]=1;
        }
        int i=0;
        while(i<m && word2.charAt(0)!=word1.charAt(i)){
        	dp[i++][0]=0;
        }
        while(i<m){
        	dp[i++][0]=1;
        }
        //逐行填,每行从左往右填
        for(i=1;i<m;i++){
        	for(j=1;j<n;j++){
        		if(word1.charAt(i)==word2.charAt(j)){
        			dp[i][j]=dp[i-1][j-1]+1;
        		}else{
        			dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
        		}
        	}
        }
        return m+n-dp[m-1][n-1]*2;//key
    }
}

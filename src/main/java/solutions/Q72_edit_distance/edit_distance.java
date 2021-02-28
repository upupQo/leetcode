// Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
//
// You have the following three operations permitted on a word:
//
//
// 	Insert a character
// 	Delete a character
// 	Replace a character
//
//
//  
// Example 1:
//
//
// Input: word1 = "horse", word2 = "ros"
// Output: 3
// Explanation: 
// horse -> rorse (replace 'h' with 'r')
// rorse -> rose (remove 'r')
// rose -> ros (remove 'e')
//
//
// Example 2:
//
//
// Input: word1 = "intention", word2 = "execution"
// Output: 5
// Explanation: 
// intention -> inention (remove 't')
// inention -> enention (replace 'i' with 'e')
// enention -> exention (replace 'n' with 'x')
// exention -> exection (replace 'n' with 'c')
// exection -> execution (insert 'u')
//
//
//  
// Constraints:
//
//
// 	0 <= word1.length, word2.length <= 500
// 	word1 and word2 consist of lowercase English letters.
//
//


class Solution {
    //72. Edit Distance.给定两个单词word1,word2,最少多少步可以让word1转化为word2,每步可以对word1添加|删除|修改一个字符
    //设置dp[i][j]为word1[0,i)编辑为word2[0,j)的最少操作数;若w1[i-1]==w2[j-1],则dp[i][j]=dp[i-1][j-1],else dp[i][j]=Min(dp[i][j-1],dp[i-1][j],dp[i-1][j-1])+1
    public int minDistance(String word1, String word2) {
        int[][] dp=new int[word1.length()+1][word2.length()+1];
        int m=word1.length()+1;
        int n=word2.length()+1;
        //初始化第一行，第一列
        for(int j=0;j<n;j++){//"" to w2[0,j)
        	dp[0][j]=j;
        }
        for(int i=0;i<m;i++){//w1[0,i) to ""
        	dp[i][0]=i;
        }
        //逐行填,每行从左往右填
        for(int i=1;i<m;i++){
        	for(int j=1;j<n;j++){
        		if(word1.charAt(i-1)==word2.charAt(j-1)){
        			dp[i][j]=dp[i-1][j-1];
        		}else{
        			dp[i][j]=Math.min(Math.min(dp[i][j-1], dp[i-1][j]),dp[i-1][j-1])+1;
        		}
        	}
        }
        return dp[m-1][n-1];
    }
}

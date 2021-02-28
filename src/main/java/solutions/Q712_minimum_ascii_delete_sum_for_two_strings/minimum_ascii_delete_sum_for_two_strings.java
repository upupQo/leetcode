// Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.
//
// Example 1:
//
// Input: s1 = "sea", s2 = "eat"
// Output: 231
// Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
// Deleting "t" from "eat" adds 116 to the sum.
// At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
//
//
//
// Example 2:
//
// Input: s1 = "delete", s2 = "leet"
// Output: 403
// Explanation: Deleting "dee" from "delete" to turn the string into "let",
// adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
// At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
// If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
//
//
//
// Note:
// 0 < s1.length, s2.length <= 1000.
// All elements of each string will have an ASCII value in [97, 122]. 
//


class Solution {
    //712. Minimum ASCII Delete Sum for Two Strings.每一次删除操作有相应的代价,求使得两个字符串相同的最小代价
    //dp[i][j]:使w1[0,i)与w2[0,j)相同的最小删除代价,若w1[i-1]==w2[j-1],那么这两个字符都可以保留，不需要删除任一个；否则dp[i][j]=min(dp[i-1][j]+ascii(w1[i-1]),dp[i][j-1]+ascii(w2[j-2]));
    //与583相比，代码相似但思路不同
    public int minimumDeleteSum(String s1, String s2) {
        int m=s1.length()+1;
        int n=s2.length()+1;
        int[][] dp=new int[m][n];
        //初始化第一行、第一列
        dp[0][0]=0;//"" and "" 不需要操作
        for(int j=1;j<n;j++){//"" and w2[0,j) 
        	dp[0][j]=dp[0][j-1]+s2.charAt(j-1);
        }
        for(int i=1;i<m;i++){//w1[0,i) and ""
        	dp[i][0]=dp[i-1][0]+s1.charAt(i-1);
        }
        //逐行填,每行从左往右填
        for(int i=1;i<m;i++){
        	for(int j=1;j<n;j++){
        		if(s1.charAt(i-1)==s2.charAt(j-1)){//w1[i-1]与w2[j-1]相同则,均保留，不需要删除
        			dp[i][j]=dp[i-1][j-1];
        		}else{
        			dp[i][j]=Math.min(dp[i-1][j]+s1.charAt(i-1), dp[i][j-1]+s2.charAt(j-1));//这一步决策删除w1[i-1]或者w2[j-1]
        		}
        	}
        }
        return dp[m-1][n-1];
    }
}

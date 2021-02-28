// A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
// The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
//
// How many possible unique paths are there?
//
//  
// Example 1:
//
//
// Input: m = 3, n = 7
// Output: 28
//
//
// Example 2:
//
//
// Input: m = 3, n = 2
// Output: 3
// Explanation:
// From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
// 1. Right -> Down -> Down
// 2. Down -> Down -> Right
// 3. Down -> Right -> Down
//
//
// Example 3:
//
//
// Input: m = 7, n = 3
// Output: 28
//
//
// Example 4:
//
//
// Input: m = 3, n = 3
// Output: 6
//
//
//  
// Constraints:
//
//
// 	1 <= m, n <= 100
// 	It's guaranteed that the answer will be less than or equal to 2 * 109.
//
//


class Solution {
    public int uniquePaths(int m, int n) {
    	if(m<=1 || n<=1){//一直直走或者不用走
    		return 1;
    	}
    	int[][] dp=new int[m][n];//dp[i][j]:从(i,j)位置到(n-1,n-1)位置有多少种不同路径
    	//1.初始化最后一行和最后一列,这些点到右下角均只有一种走法
    	for(int j=0;j<n;j++){
    		dp[m-1][j]=1;
    	}
    	for(int i=0;i<m;i++){
    		dp[i][n-1]=1;
    	}
    	//2.逐行填,每行从右往左填
    	for(int i=m-2;i>=0;i--){
    		for(int j=n-2;j>=0;j--){
    			dp[i][j]=dp[i+1][j]+dp[i][j+1];
    		}
    	}
    	return dp[0][0];
    }
}

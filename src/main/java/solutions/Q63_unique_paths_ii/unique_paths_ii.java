// A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
// The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
//
// Now consider if some obstacles are added to the grids. How many unique paths would there be?
//
// An obstacle and space is marked as 1 and 0 respectively in the grid.
//
//  
// Example 1:
//
//
// Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
// Output: 2
// Explanation: There is one obstacle in the middle of the 3x3 grid above.
// There are two ways to reach the bottom-right corner:
// 1. Right -> Right -> Down -> Down
// 2. Down -> Down -> Right -> Right
//
//
// Example 2:
//
//
// Input: obstacleGrid = [[0,1],[0,0]]
// Output: 1
//
//
//  
// Constraints:
//
//
// 	m == obstacleGrid.length
// 	n == obstacleGrid[i].length
// 	1 <= m, n <= 100
// 	obstacleGrid[i][j] is 0 or 1.
//
//


class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	int m=obstacleGrid.length;
    	int n=obstacleGrid[0].length;
        int[][] dp=new int[m][n];
        //1.初始化最后一行和最后一列
        int j=n-1;
        while(j>=0 && obstacleGrid[m-1][j]==0){
        	dp[m-1][j--]=1;
        }
        while(j>=0){//最后一行一旦出现不可达点则该点及前面的点均不可达,标记为0
        	dp[m-1][j--]=0;
        }
        int i=m-1;
        while(i>=0 && obstacleGrid[i][n-1]==0){
        	dp[i--][n-1]=1;
        }
        while(i>=0){
        	dp[i--][n-1]=0;
        }
        //2.逐行填,每行从右往左填
        for(i=m-2;i>=0;i--){
        	for(j=n-2;j>=0;j--){
        		if(obstacleGrid[i][j]==1){
        			dp[i][j]=0;
        		}else{
        			dp[i][j]=dp[i+1][j]+dp[i][j+1];
        		}
        	}
        }
        return dp[0][0];
    }
}

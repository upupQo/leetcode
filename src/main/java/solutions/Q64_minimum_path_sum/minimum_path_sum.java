// Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
//
// Note: You can only move either down or right at any point in time.
//
//  
// Example 1:
//
//
// Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
// Output: 7
// Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
//
//
// Example 2:
//
//
// Input: grid = [[1,2,3],[4,5,6]]
// Output: 12
//
//
//  
// Constraints:
//
//
// 	m == grid.length
// 	n == grid[i].length
// 	1 <= m, n <= 200
// 	0 <= grid[i][j] <= 100
//
//


class Solution {
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int[][] dp=new int[m][n];
        //1.初始化最后一行、最后一列
        dp[m-1][n-1]=grid[m-1][n-1];
        for(int j=n-2;j>=0;j--){
        	dp[m-1][j]=grid[m-1][j]+dp[m-1][j+1];
        }
        for(int i=m-2;i>=0;i--){
        	dp[i][n-1]=grid[i][n-1]+dp[i+1][n-1];
        }
       //2.逐行填,每行从右往左填
        for(int i=m-2;i>=0;i--){
        	for(int j=n-2;j>=0;j--){
        		dp[i][j]=grid[i][j]+Math.min(dp[i+1][j], dp[i][j+1]);
        	}
        }
        return dp[0][0];
    }
    //根据dp矩阵还原路径
    private List<Integer> getPath(int[][] dp,int[][] grid){
    	int m=dp.length;
    	int n=dp[0].length;
    	int x=0,y=0;
    	List<Integer> path=new ArrayList<Integer>();
    	while(!(x==m-1 || y==n-1)){
    		path.add(grid[x][y]);
    		if(dp[x+1][y]<=dp[x][y+1]){
    			x++;
    		}else{
    			y++;
    		}
    	}
    	if(x==m-1 && y==n-1){
    		return path;
    	}else if(x==m-1){//到了最后一行
    		while(y<=n-1){
    			path.add(grid[x][y++]);
    		}
    	}else{//到了最后一列
    		while(x<=m-1){
    			path.add(grid[x++][y]);
    		}
    	}
    	return path;
    }
}

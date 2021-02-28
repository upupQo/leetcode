// Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
//
// An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//
//  
// Example 1:
//
//
// Input: grid = [
//   ["1","1","1","1","0"],
//   ["1","1","0","1","0"],
//   ["1","1","0","0","0"],
//   ["0","0","0","0","0"]
// ]
// Output: 1
//
//
// Example 2:
//
//
// Input: grid = [
//   ["1","1","0","0","0"],
//   ["1","1","0","0","0"],
//   ["0","0","1","0","0"],
//   ["0","0","0","1","1"]
// ]
// Output: 3
//
//
//  
// Constraints:
//
//
// 	m == grid.length
// 	n == grid[i].length
// 	1 <= m, n <= 300
// 	grid[i][j] is '0' or '1'.
//
//


class Solution {
    	private char[][] grid;
        public int numIslands(char[][] grid) {
            if(grid.length==0){
                return 0;
            }
            this.grid=grid;
            //先预处理边界
            int m=grid.length;
            int n=grid[0].length;
            //开发出发并标记
            int count=0;
            for(int i=0;i<m;i++){
            	for(int j=0;j<n;j++){
            		if(grid[i][j]=='1'){//未访问过的新大陆的起点
            			searchWithDfs(i, j);
            			count++;
            		}
            	}
            }
            return count;
        }
        //从i,j位置可以到达的所有位置,i,j是一个可达点。DFS
        public void searchWithDfs(int i,int j){
        	grid[i][j]='0';//key
        	int[][] directions=new int[][]{{1,0},{0,1},{-1,0},{0,-1}};//下右上左
        	for(int k=0;k<=3;k++){
        		int x=i+directions[k][0];
        		int y=j+directions[k][1];
        		if(x<0 || x==grid.length || y<0 || y==grid[0].length || grid[x][y]=='0'){
        			continue;
        		}
        		searchWithDfs(x,y);
        	}
        }
    }


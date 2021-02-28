// Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
//
// Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
//
// Example 1:
//
//
// [[0,0,1,0,0,0,0,1,0,0,0,0,0],
//  [0,0,0,0,0,0,0,1,1,1,0,0,0],
//  [0,1,1,0,1,0,0,0,0,0,0,0,0],
//  [0,1,0,0,1,1,0,0,1,0,1,0,0],
//  [0,1,0,0,1,1,0,0,1,1,1,0,0],
//  [0,0,0,0,0,0,0,0,0,0,1,0,0],
//  [0,0,0,0,0,0,0,1,1,1,0,0,0],
//  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
//
// Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
//
// Example 2:
//
//
// [[0,0,0,0,0,0,0,0]]
// Given the above grid, return 0.
//
// Note: The length of each dimension in the given grid does not exceed 50.
//


class Solution {
    	private int[][] grid;
        public int maxAreaOfIsland(int[][] grid) {
            if(grid.length==0){
            	return 0;
            }
            int m=grid.length;
            int n=grid[0].length;
            this.grid=grid;
            int maxArea=0;
            for(int i=0;i<m;i++){
            	for(int j=0;j<n;j++){
            		if(grid[i][j]==1){
            			maxArea=Math.max(maxArea, areaWithBFS(i*grid[0].length+j));
            		}
            	}
            }
            return maxArea;
        }
        //用深度优先找(i,j)所在岛屿的面积
        public int areaWithDFS(int i,int j){
        	int area=1;
        	grid[i][j]=0;
        	int[][] directions=new int[][]{{1,0},{0,1},{-1,0},{0,-1}};//下右上左
        	for(int k=0;k<=3;k++){
        		int x=i+directions[k][0];
        		int y=j+directions[k][1];
        		if(x<0 || x==grid.length || y<0 || y==grid[0].length || grid[x][y]!=1){
        			continue;
        		}
        		area+=areaWithDFS(x, y);
        	}
        	return area;
        }
        //用广度优先找(i,j)所在岛屿的面积。用i*grid[0].length+j来唯一表示一个位置
        //notice.入队的时候改book
        public int areaWithBFS(int pos){
        	Queue<Integer> queue=new LinkedList<Integer>();
        	grid[pos/grid[0].length][pos%grid[0].length]=0;//表示已经访问过或者已经在访问队列
        	queue.add(pos);
        	int[][] directions=new int[][]{{1,0},{0,1},{-1,0},{0,-1}};//下右上左
        	int area=0;
        	while(!queue.isEmpty()){
        		int curPos=queue.poll();
        		int i=curPos/grid[0].length;
        		int j=curPos%grid[0].length;
        		area++;
        		for(int k=0;k<=3;k++){
        			int x=i+directions[k][0];
        			int y=j+directions[k][1];
        			if(x<0 || x==grid.length || y<0 || y==grid[0].length || grid[x][y]!=1){
        				continue;
        			}
        			grid[x][y]=0;
        			queue.add(x*grid[0].length+y);
        		}
        	}
        	return area;
        }      
}

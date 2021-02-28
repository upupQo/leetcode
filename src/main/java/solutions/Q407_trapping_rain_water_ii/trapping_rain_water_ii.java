// Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.
//
// Example:
//
//
// Given the following 3x6 height map:
// [
//   [1,4,3,1,3,2],
//   [3,2,1,3,2,4],
//   [2,3,3,2,3,1]
// ]
//
// Return 4.
//
//
//
//
// The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.
//
//  
//
//
//
// After the rain, water is trapped between the blocks. The total volume of water trapped is 4.
//
//  
// Constraints:
//
//
// 	1 <= m, n <= 110
// 	0 <= heightMap[i][j] <= 20000
//
//


class Solution{
    	private class Cell implements Comparable<Cell>{
    		int row;
    		int col;
    		int height;
			public Cell(int row, int col, int height) {
				this.row = row;
				this.col = col;
				this.height = height;
			}
			@Override
			public int compareTo(Cell o) {
				return this.height- o.height;
			}
    	} 
        public int trapRainWater(int[][] heightMap) {
        	if(heightMap.length<=1 || heightMap[0].length<=1){//<=1行或每行<=1个元素,没法3维接水
        		return 0;
        	}
            boolean[][] visited=new boolean[heightMap.length][heightMap[0].length];//默认被初始化为false
            PriorityQueue<Cell> queue=new PriorityQueue<Cell>();//小堆
            int waterTraped=0;
            //1.初始化把最外围圈入队
            for(int j=0;j<heightMap[0].length;j++){//上下行
            	queue.add(new Cell(0, j, heightMap[0][j]));
            	queue.add(new Cell(heightMap.length-1,j,heightMap[heightMap.length-1][j]));
            	visited[0][j]=true;
            	visited[heightMap.length-1][j]=true;
            }
            for(int i=1;i<heightMap.length-1;i++){//左右列，notice顶点不要重复添加，因此...from 1 to length-2
            	queue.add(new Cell(i, 0, heightMap[i][0]));
            	queue.add(new Cell(i,heightMap[0].length-1,heightMap[i][heightMap[0].length-1]));
            	visited[i][0]=true;
            	visited[i][heightMap[0].length-1]=true;
            }
            //2.逐点收集雨水--通过优先队列找短板
            Cell lowestWall;//最矮的墙
            int row,col;
            int[][] direction={{1,0},{0,1},{-1,0},{0,-1}};//下右上左四个方向
            while(!queue.isEmpty()){
            	lowestWall=queue.poll();
            	for(int i=0;i<4;i++){
            		row=lowestWall.row+direction[i][0];
            		col=lowestWall.col+direction[i][1];
            		if(row<0 || row>heightMap.length-1 || col<0 || col>heightMap[0].length-1 || visited[row][col]==true){//越界检查+已经计算检查
            			continue;
            		}
            		waterTraped+=Math.max(lowestWall.height-heightMap[row][col], 0);//当前单元格高<lowestWall高，则可以接至lowestWall.height,否则不能接水
            		queue.add(new Cell(row,col,Math.max(lowestWall.height, heightMap[row][col])));//key point.加入队列成为新墙，墙高取大的
            		visited[row][col]=true;
            	}
            }
            return waterTraped;
        }
    }

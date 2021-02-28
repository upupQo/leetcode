// Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest element in the matrix.
//
// Note that it is the kth smallest element in the sorted order, not the kth distinct element.
//
//  
// Example 1:
//
//
// Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
// Output: 13
// Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
//
//
// Example 2:
//
//
// Input: matrix = [[-5]], k = 1
// Output: -5
//
//
//  
// Constraints:
//
//
// 	n == matrix.length
// 	n == matrix[i].length
// 	1 <= n <= 300
// 	-109 <= matrix[i][j] <= -109
// 	All the rows and columns of matrix are guaranteed to be sorted in non-degreasing order.
// 	1 <= k <= n2
//
//


class Solution {
        //378.第K小元素--优先队列的BFS
    private class Cell implements Comparable<Cell>{
    	private int row;
    	private int col;
    	private int val;
    	public Cell(int row,int col,int val){
    		this.row=row;
    		this.col=col;
    		this.val=val;
    	}
		@Override
		public int compareTo(Cell o) {
			// TODO Auto-generated method stub
			return this.val-o.val;
		}
    }
    public int kthSmallest(int[][] matrix, int k) {
    	PriorityQueue<Cell> queue=new PriorityQueue<Cell>();
    	int[][] directions={{1,0},{0,1},{-1,0},{0,-1}};//下右上左四个方向
    	int m=matrix.length,n=matrix[0].length;
    	boolean[][] book=new boolean[m][n];//记录某个结点是否已经进过栈
    	queue.add(new Cell(0,0,matrix[0][0]));
    	book[0][0]=true;
    	while(!queue.isEmpty()){
    		Cell current=queue.poll();
    		if(k==1){
    			return current.val;
    		}
    		k--;
    		for(int i=0;i<4;i++){
    			int x=current.row+directions[i][0];
    			int y=current.col+directions[i][1];
    			if(x<0 || x==m || y<0 || y==n || book[x][y]){
    				continue;
    			}
    			queue.add(new Cell(x, y, matrix[x][y]));
    			book[x][y]=true;
    		}
    	}
    	return -1;
    }
}

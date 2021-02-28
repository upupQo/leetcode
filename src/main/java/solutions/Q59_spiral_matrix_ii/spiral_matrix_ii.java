// Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
//
//  
// Example 1:
//
//
// Input: n = 3
// Output: [[1,2,3],[8,9,4],[7,6,5]]
//
//
// Example 2:
//
//
// Input: n = 1
// Output: [[1]]
//
//
//  
// Constraints:
//
//
// 	1 <= n <= 20
//
//


class Solution {
    public int[][] generateMatrix(int n) {
    	int[][] matrix=new int[n][n];
    	int top=0,left=0,down=n-1,right=n-1;
    	int val=1;
    	while(val<=n*n){//也可以依它为边界条件
    		int x=top,y=left;
    		//1.填上边
    		while(y<=right){
    			matrix[x][y++]=val++;
    		}
    		x++;y--;
    		//填右边
    		while(x<=down){
    			matrix[x++][y]=val++;
    		}
    		x--;y--;
    		//填下边
    		while(y>=left){
    			matrix[x][y--]=val++;
    		}
    		x--;y++;
    		//填左边
    		while(x>top){//notice,不能再取=
    			matrix[x--][y]=val++;
    		}
    		top++;left++;
    		down--;right--;
    	}
    	return matrix;
    }
}

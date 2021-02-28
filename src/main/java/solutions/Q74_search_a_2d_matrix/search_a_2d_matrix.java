// Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
//
//
// 	Integers in each row are sorted from left to right.
// 	The first integer of each row is greater than the last integer of the previous row.
//
//
//  
// Example 1:
//
//
// Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
// Output: true
//
//
// Example 2:
//
//
// Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
// Output: false
//
//
//  
// Constraints:
//
//
// 	m == matrix.length
// 	n == matrix[i].length
// 	1 <= m, n <= 100
// 	-104 <= matrix[i][j], target <= 104
//
//


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length==0){
    		return false;
    	}
        int low=0,high=matrix.length-1;
        int col=matrix[0].length-1;
        //1.二分确定行
        while(low<high){
        	int midRow=(low+high)/2;
        	if(matrix[midRow][col]<target){
        		low=midRow+1;
        	}else{
        		high=midRow;
        	}
        }
        //2.对该行进行二分查找
        int row=low;
        low=0;high=col;
        while(low<high){
        	int mid=(low+high)/2;
        	if(matrix[row][mid]<target){
        		low=mid+1;
        	}else{
        		high=mid;
        	}
        }
        return matrix[row][low]==target;
    }
}

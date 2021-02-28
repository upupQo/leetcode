// Given an m x n matrix, return all elements of the matrix in spiral order.
//
//  
// Example 1:
//
//
// Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
// Output: [1,2,3,6,9,8,7,4,5]
//
//
// Example 2:
//
//
// Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
// Output: [1,2,3,4,8,12,11,10,9,5,6,7]
//
//
//  
// Constraints:
//
//
// 	m == matrix.length
// 	n == matrix[i].length
// 	1 <= m, n <= 10
// 	-100 <= matrix[i][j] <= 100
//
//


public class Solution {
 public List<Integer> spiralOrder(int[][] matrix) {
	   List<Integer> res=new ArrayList<Integer>();
	   if(matrix==null || matrix.length==0 || matrix[0].length==0)
		   return res;
	   int top=0,bottom=matrix.length-1;
	   int left=0,right=matrix[0].length-1;
	   while(top<=bottom && left<=right){
		   if(left==right){//单列,自上往下打
			   int temp=top;
			   while(temp<=bottom)
				   res.add(matrix[temp++][left]);
		   }else if(top==bottom){//单行，自左往右打
			   int temp=left;
			   while(temp<=right)
				   res.add(matrix[top][temp++]);
		   }else{//多行多列
			   int temp=left;
			   while(temp<=right)//往左
				   res.add(matrix[top][temp++]);
			   temp=top+1;//往下
			   while(temp<=bottom)
				   res.add(matrix[temp++][right]);
			   temp=right-1;//往左
			   while(temp>=left)
				   res.add(matrix[bottom][temp--]);
			   temp=bottom-1;
			   while(temp>top)//往上
				   res.add(matrix[temp--][left]);
		   }
		   top++;bottom--;
		   left++;right--;
	   }
	   return res;
   }
}

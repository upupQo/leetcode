// You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
//
// You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
//
//  
// Example 1:
//
//
// Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
// Output: [[7,4,1],[8,5,2],[9,6,3]]
//
//
// Example 2:
//
//
// Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
// Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
//
//
// Example 3:
//
//
// Input: matrix = [[1]]
// Output: [[1]]
//
//
// Example 4:
//
//
// Input: matrix = [[1,2],[3,4]]
// Output: [[3,1],[4,2]]
//
//
//  
// Constraints:
//
//
// 	matrix.length == n
// 	matrix[i].length == n
// 	1 <= n <= 20
// 	-1000 <= matrix[i][j] <= 1000
//
//


class Solution {
    public void rotate(int[][] matrix) {
        if(matrix.length<=1 || matrix[0].length!=matrix.length){//不是正方形的矩形无法有效旋转,只有一个元素无需旋转
        	return;
        }
        int circle=matrix.length/2;//转圈旋转,需要旋转的圈数
        int left=0,top=0,right=matrix.length-1,down=matrix[0].length-1;//(top,left)标记左上角,(down,right)标记右下角,通过左上角与右下角就可以唯一确定一个圈
        while(circle>0){
        	int offset=0;
        	int val1,val2;
        	while(offset<right-left){//key
        		//1.右列置为上行
        		val1=matrix[top+offset][right];
        		val2=matrix[top][left+offset];
        		matrix[top+offset][right]=val2;
        		//2.下行置为右列
        		val2=matrix[down][right-offset];
        		matrix[down][right-offset]=val1;
        		//3.左列置为下行
        		val1=matrix[down-offset][left];
        		matrix[down-offset][left]=val2;
        		//4.上行置为左列
        		matrix[top][left+offset]=val1;
        		offset++;
        	}
        	top++;//向下进1
        	left++;
        	right--;
        	down--;//向上进1
        	circle--;
        }
    }
}

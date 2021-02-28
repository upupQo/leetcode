// Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
//
//  
// Example 1:
//
//
// Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
// Output: 6
// Explanation: The maximal rectangle is shown in the above picture.
//
//
// Example 2:
//
//
// Input: matrix = []
// Output: 0
//
//
// Example 3:
//
//
// Input: matrix = [["0"]]
// Output: 0
//
//
// Example 4:
//
//
// Input: matrix = [["1"]]
// Output: 1
//
//
// Example 5:
//
//
// Input: matrix = [["0","0"]]
// Output: 0
//
//
//  
// Constraints:
//
//
// 	rows == matrix.length
// 	cols == matrix.length
// 	0 <= row, cols <= 200
// 	matrix[i][j] is '0' or '1'.
//
//


public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
    		return 0;
    	int[] help=new int[matrix[0].length];
    	int maxArea=0;//面积至少为0
    	for(int i=0;i<matrix.length;i++)//枚举首行
    	{
    			for(int j=0;j<matrix[0].length;j++)
    				help[j]=matrix[i][j]=='1'?(help[j]+1):0;
    			maxArea=Math.max(maxArea, largestRectangleArea(help));
    	}
    	return maxArea;
    }
    public int largestRectangleArea(int[] heights){
    	if(heights==null || heights.length==0)
    		return 0;
    	int maxArea=0;
    	Stack<Integer> stack=new Stack<Integer>();
    	for(int i=0;i<heights.length;i++){
    		if(stack.isEmpty() || heights[stack.peek()]<heights[i])
    		{
    			stack.push(i);
    			continue;
    		}
    		while(!stack.isEmpty() && heights[i]<=heights[stack.peek()])
    		{
    			int index=stack.pop();
    			int area;
				if(stack.isEmpty())//左边没有比自己小的
    				 area=(i)*heights[index];//一个(右)端点不包含
    			else
    				 area=(i-stack.peek()-1)*heights[index];//宽*高，两个端点不包含
    			maxArea=maxArea>area?maxArea:area;
    		}
    		stack.push(i);
    	}
    	while(!stack.isEmpty()){//右边没有比自己小的
    		int index=stack.pop();
    		int area=0;
    		if(stack.isEmpty())
    			area=(heights.length)*heights[index];//两个端点均包含
    		else
    			area=(heights.length-1-stack.peek())*heights[index];//一个端点不包含
    		maxArea=maxArea>area?maxArea:area;
    	}
    	return maxArea;
    }
}

// Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
//
//  
//
//
// Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
//
//  
//
//
// The largest rectangle is shown in the shaded area, which has area = 10 unit.
//
//  
//
// Example:
//
//
// Input: [2,1,5,6,2,3]
// Output: 10
//
//
//  
// Example 1:
//
//
// Input: heights = [2,1,5,6,2,3]
// Output: 10
// Explanation: The above is a histogram where width of each bar is 1.
// The largest rectangle is shown in the red area, which has an area = 10 units.
//
//
// Example 2:
//
//
// Input: heights = [2,4]
// Output: 4
//
//
//  
// Constraints:
//
//
// 	1 <= heights.length <= 105
// 	0 <= heights[i] <= 104
//
//


public class Solution {
    public int largestRectangleArea(int[] heights) {
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

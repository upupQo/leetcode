// Given a triangle array, return the minimum path sum from top to bottom.
//
// For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
//
//  
// Example 1:
//
//
// Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
// Output: 11
// Explanation: The triangle looks like:
//    2
//   3 4
//  6 5 7
// 4 1 8 3
// The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
//
//
// Example 2:
//
//
// Input: triangle = [[-10]]
// Output: -10
//
//
//  
// Constraints:
//
//
// 	1 <= triangle.length <= 200
// 	triangle[0].length == 1
// 	triangle[i].length == triangle[i - 1].length + 1
// 	-104 <= triangle[i][j] <= 104
//
//
//  
// Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?


class Solution {
    public int minimumTotalI(List<List<Integer>> triangle) {//1.自顶向下,会改变输入
    	List<Integer> preLine;
        List<Integer> currentLine=triangle.get(0);//当前所在行
        for(int i=1;i<triangle.size();i++){
        	preLine=triangle.get(i-1);
        	currentLine=triangle.get(i);
        	currentLine.set(0, currentLine.get(0)+preLine.get(0));
        	for(int j=1;j<currentLine.size()-1;j++){
        		currentLine.set(j, currentLine.get(j)+Math.min(preLine.get(j-1), preLine.get(j)));
        	}
        	currentLine.set(currentLine.size()-1, currentLine.get(currentLine.size()-1)+preLine.get(currentLine.size()-2));
            //更新后的currentLine(i)代表从顶部到达该行的i号元素的最小路径和，这样通过局部最优逐渐获得整体最优
        }
        int minSum=Integer.MAX_VALUE;
        //遍历更新后的最后一行，找到最小值
        for(int i=0;i<currentLine.size();i++){
        	minSum=Math.min(minSum, currentLine.get(i));
        }
        return minSum;
    }
    public int minimumTotal(List<List<Integer>> triangle) {//2.自底向上,从下层往上层走，每次也只能往相邻的位置走,貌似更简洁。
    	List<Integer> preLine=triangle.get(0);//当前行的上一行
        List<Integer> currentLine;//当前所在行
        for(int i=triangle.size()-1;i>0;i--){
        	currentLine=triangle.get(i);
        	preLine=triangle.get(i-1);
        	//通过当前行往上走更新上一行
        	for(int j=0;j<preLine.size();j++){
        		preLine.set(j, preLine.get(j)+Math.min(currentLine.get(j), currentLine.get(j+1)));
        	}
        }
        return preLine.get(0);
    }
}

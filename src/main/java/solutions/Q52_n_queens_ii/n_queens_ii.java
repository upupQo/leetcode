// The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
//
// Given an integer n, return the number of distinct solutions to the n-queens puzzle.
//
//  
// Example 1:
//
//
// Input: n = 4
// Output: 2
// Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
//
//
// Example 2:
//
//
// Input: n = 1
// Output: 1
//
//
//  
// Constraints:
//
//
// 	1 <= n <= 9
//
//


class Solution {
    private int queensSolutionsCount=0;
    public int totalNQueens(int n) {
        boolean[][] help=new boolean[3][2*n-1];
        totalNQueens(0, n, help);
        return queensSolutionsCount;
    }
    public void totalNQueens(int i,int n,boolean[][] help) {
    	if(i==n){
    		queensSolutionsCount++;
    		return;
    	}
    	for(int j=0;j<n;j++){
    		if(help[0][j]==true || help[1][i-j+n-1]==true || help[2][i+j]==true){//第j列已经放了皇后 || 135°已放 || 45°以放
    			continue;
    		}else{
                help[0][j]=true;help[1][i-j+n-1]=true;help[2][i+j]=true;
	    		totalNQueens(i+1, n, help);
	    		help[0][j]=false;help[1][i-j+n-1]=false;help[2][i+j]=false;
    		}
    	}
    }
}

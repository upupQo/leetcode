// Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
//
//
// 	Each row must contain the digits 1-9 without repetition.
// 	Each column must contain the digits 1-9 without repetition.
// 	Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
//
//
// Note:
//
//
// 	A Sudoku board (partially filled) could be valid but is not necessarily solvable.
// 	Only the filled cells need to be validated according to the mentioned rules.
//
//
//  
// Example 1:
//
//
// Input: board = 
// [["5","3",".",".","7",".",".",".","."]
// ,["6",".",".","1","9","5",".",".","."]
// ,[".","9","8",".",".",".",".","6","."]
// ,["8",".",".",".","6",".",".",".","3"]
// ,["4",".",".","8",".","3",".",".","1"]
// ,["7",".",".",".","2",".",".",".","6"]
// ,[".","6",".",".",".",".","2","8","."]
// ,[".",".",".","4","1","9",".",".","5"]
// ,[".",".",".",".","8",".",".","7","9"]]
// Output: true
//
//
// Example 2:
//
//
// Input: board = 
// [["8","3",".",".","7",".",".",".","."]
// ,["6",".",".","1","9","5",".",".","."]
// ,[".","9","8",".",".",".",".","6","."]
// ,["8",".",".",".","6",".",".",".","3"]
// ,["4",".",".","8",".","3",".",".","1"]
// ,["7",".",".",".","2",".",".",".","6"]
// ,[".","6",".",".",".",".","2","8","."]
// ,[".",".",".","4","1","9",".",".","5"]
// ,[".",".",".",".","8",".",".","7","9"]]
// Output: false
// Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
//
//
//  
// Constraints:
//
//
// 	board.length == 9
// 	board[i].length == 9
// 	board[i][j] is a digit or '.'.
//
//


class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows=new boolean[9][9];//rows[i][j]第i行是否已经使用了值j+1
        boolean[][] cols=new boolean[9][9];//cols[i][j]第i列是否已经使用了值j+1
        boolean[][] subBox=new boolean[9][9];//subBox[i][j]第i个小九宫格是否已经使用了值j+1。九宫格按行来，每行从左往右,board[i][j]说属的小九宫格为(i/3)*3+j/3;(i/3)*3为基数(利用除,去尾数),j/3为相对偏移
        //逐行验证，每行从左往右验证
        int val;
        int subBoxIndex;
        for(int i=0;i<9;i++){
        	for(int j=0;j<9;j++){
        		if(board[i][j]!='.'){
        			val=board[i][j]-'0';//notice
                    subBoxIndex=(i/3)*3+j/3;
        			if(val<=0 || val>9 || rows[i][val-1]==true || cols[j][val-1]==true || subBox[subBoxIndex][val-1]==true){
        				return false;
        			}
        			rows[i][val-1]=true;
        			cols[j][val-1]=true;
        			subBox[subBoxIndex][val-1]=true;
        		}
        	}
        }
        return true;
    }
}

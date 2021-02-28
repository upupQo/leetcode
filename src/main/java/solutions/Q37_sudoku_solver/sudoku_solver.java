// Write a program to solve a Sudoku puzzle by filling the empty cells.
//
// A sudoku solution must satisfy all of the following rules:
//
//
// 	Each of the digits 1-9 must occur exactly once in each row.
// 	Each of the digits 1-9 must occur exactly once in each column.
// 	Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
//
//
// The '.' character indicates empty cells.
//
//  
// Example 1:
//
//
// Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
// Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
// Explanation: The input board is shown above and the only valid solution is shown below:
//
//
//
//
//  
// Constraints:
//
//
// 	board.length == 9
// 	board[i].length == 9
// 	board[i][j] is a digit or '.'.
// 	It is guaranteed that the input board has only one solution.
//
//


class Solution {
    	private char[][] board;
        private boolean[][] rows;
        private boolean[][] cols;
        private boolean[][] subBox;
        public void solveSudoku(char[][] board) {
            this.board=board;
            this.rows=new boolean[9][9];//rows[i][j]第i行是否已经使用了值j+1
            this.cols=new boolean[9][9];//cols[i][j]第i列是否已经使用了值j+1
            this.subBox=new boolean[9][9];//subBox[i][j]第i个小九宫格是否已经使用了值j+1。九宫格按行来，每行从左往右,board[i][j]说属的小九宫格为(i/3)*3+j/3;(i/3)*3为基数(利用除,去尾数),j/3为相对偏移
            //1.初始化标记
            int val;
            for(int i=0;i<9;i++){
            	for(int j=0;j<9;j++){
            		if(board[i][j]!='.'){
            			val=board[i][j]-'0';//notice
            			rows[i][val-1]=true;
            			cols[j][val-1]=true;
            			subBox[(i/3)*3+j/3][val-1]=true;
            		}
            	}
            }
            //2.从(0,0)位置开始填
            fillSudoku(0, 0);
        }
        //填充数独,从(i,j)位置是否可以将数独填充完
        private boolean fillSudoku(int i,int j){
        	if(i==9){//已经成功填完
        		return true;
        	}
        	//计算下一个位置
			int x=i,y=j;
			if(y==8){//要换行
				x++;
				y=0;
			}else{
				y++;
			}
        	if(board[i][j]=='.'){//需要填充
        		for(int k=1;k<=9;k++){
        			if(!rows[i][k-1] && !cols[j][k-1] && !subBox[(i/3)*3+j/3][k-1]){//k值可以使用
            			rows[i][k-1]=true;
            			cols[j][k-1]=true;
            			subBox[(i/3)*3+j/3][k-1]=true;
            			//递归
            			if(fillSudoku(x, y)){//在(i,j)位置用k值可以填充完毕，则返回；否则回溯尝试下一个可选k值
                            board[i][j]=(char)(k+'0');//别忘了
            				return true;
            			}
            			//回溯
            			rows[i][k-1]=false;
            			cols[j][k-1]=false;
            			subBox[(i/3)*3+j/3][k-1]=false;
        			}
        		}
        		return false;
        	}else{//不需要填充
        		return fillSudoku(x, y);
        	}
        }
    }

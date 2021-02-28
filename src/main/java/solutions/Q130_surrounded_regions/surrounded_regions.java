// Given an m x n matrix board containing 'X' and 'O', capture all regions surrounded by 'X'.
//
// A region is captured by flipping all 'O's into 'X's in that surrounded region.
//
//  
// Example 1:
//
//
// Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
// Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
// Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
//
//
// Example 2:
//
//
// Input: board = [["X"]]
// Output: [["X"]]
//
//
//  
// Constraints:
//
//
// 	m == board.length
// 	n == board[i].length
// 	1 <= m, n <= 200
// 	board[i][j] is 'X' or 'O'.
//
//


class Solution {
    public void solve(char[][] board) {
        if(board.length==0 || board[0].length==0) {
    		return;
    	}
        boolean book[][]=new boolean[board.length][board[0].length];//book[i][j]==true,是'O'点，且与边界某个'O'连通
        //1.左右两列
        for(int i=0;i<board.length;i++){
            if(board[i][0]=='O'){
                bookWithDFS(board,book,i,0);
            }
            if(board[i][board[0].length-1]=='O'){
                bookWithDFS(board,book,i,board[0].length-1);
            }
        }
        //2.上下两行
        for(int j=0;j<board[0].length;j++){
            if(board[0][j]=='O'){
                bookWithDFS(board,book,0,j);
            }
            if(board[board.length-1][j]=='O'){
                bookWithDFS(board,book,board.length-1,j);
            }
        }
        //涂表
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(!book[i][j]){
                    board[i][j]='X';
                }
            }
        }
    }
    //从'O'点出发,访问所有相邻点
    public void bookWithDFS(char[][] board,boolean[][] book,int i,int j){
        int directions[][]=new int[][]{{-1,0},{0,1},{1,0},{0,-1}};//定义上右下左
        book[i][j]=true;//标记为已访问
        for(int k=0;k<=3;k++){
            int x=i+directions[k][0];
            int y=j+directions[k][1];
            if(x<0 || x==board.length || y<0 || y==board[0].length || board[x][y]=='X' || book[x][y]==true){//'X'点不应该访问 ，已经访问过的'O'不能再访问
                continue;
            }
            bookWithDFS(board,book,x,y);
        }
    }
}

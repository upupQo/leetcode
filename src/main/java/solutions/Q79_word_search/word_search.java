// Given an m x n board and a word, find if the word exists in the grid.
//
// The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
//  
// Example 1:
//
//
// Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
// Output: true
//
//
// Example 2:
//
//
// Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
// Output: true
//
//
// Example 3:
//
//
// Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
// Output: false
//
//
//  
// Constraints:
//
//
// 	m == board.length
// 	n = board[i].length
// 	1 <= m, n <= 200
// 	1 <= word.length <= 103
// 	board and word consists only of lowercase and uppercase English letters.
//
//


class Solution {
    public boolean exist(char[][] board, String word) {
    	boolean[][] book=new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
        	for(int j=0;j<board[0].length;j++){
        		if(search(board, i, j, word, 0, book)){
        			return true;
        		}
        	}
        }
        return false;
    }
    //从board[i,j]位置搜索str[index,n-1]
    public boolean search(char[][] board,int i,int j,String word,int index,boolean[][] book){
    	if(index==word.length()){
    		return true;
    	}
    	if(board[i][j]!=word.charAt(index)){
    		return false;
    	}
        if(index==word.length()-1){
    		return true;
    	}
    	book[i][j]=true;
    	int[][] directions=new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    	for(int k=0;k<=3;k++){
    		int x=i+directions[k][0],y=j+directions[k][1];
    		if(x<0 || x==board.length || y<0 || y==board[0].length || book[x][y]==true){
    			continue;
    		}
    		if(search(board, x, y, word, index+1, book)){
    			return true;
    		}
    	}
    	book[i][j]=false;//回溯
    	return false;
    }
}

// The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
//
// Given an integer n, return all distinct solutions to the n-queens puzzle.
//
// Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
//
//  
// Example 1:
//
//
// Input: n = 4
// Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
// Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
//
//
// Example 2:
//
//
// Input: n = 1
// Output: [["Q"]]
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
    	private int n;
    	private boolean[][] help;
    	private List<List<String>> solutions;
    	private ArrayList<Integer> indexs;//int[i]==j:第i行应该在j位置出放皇后
        public List<List<String>> solveNQueens(int n) {
        	this.n=n;
        	this.help=new boolean[3][2*n-1];//关键辅助数组help[0][k]第k列是否已放,help[1][k]:j-i+n-1==k的135度对角线是否已放了皇后,help[2][k]:i+j==k的45度对角线是否已经放了皇后
            this.solutions=new ArrayList<List<String>>();
        	this.indexs=new ArrayList<Integer>();
        	search(0);
        	return solutions;
        }
        private void search(int i){//当前在第i行
        	if(i==n){
        		solutions.add(constructSolution());
        		return;
        	}
        	for(int j=0;j<n;j++){//尝试该行的n个位置
        		if(help[0][j]==true || help[1][i-j+n-1]==true || help[2][i+j]==true){//第j列已经放了皇后 || 135°已放 || 45°以放
        			continue;
        		}
        		indexs.add(j);
        		help[0][j]=true;help[1][i-j+n-1]=true;help[2][i+j]=true;
        		search(i+1);//试探下一行
        		//回溯试探该行下一个位置
        		indexs.remove(indexs.size()-1);
        		help[0][j]=false;help[1][i-j+n-1]=false;help[2][i+j]=false;
        	}
        }
        //构造解
        private List<String> constructSolution(){
        	List<String> res=new ArrayList<String>();
        	StringBuilder sb;
        	for(int i=0;i<n;i++){//构造行
        		sb=new StringBuilder();
        		int j=0;
        		while(j<indexs.get(i)){
        			sb.append('.');
                    j++;
        		}
        		sb.append('Q');
        		j++;
        		while(j<n){
        			sb.append('.');
                    j++;
        		}
        		res.add(sb.toString());
        	}
        	return res;
        }
    }

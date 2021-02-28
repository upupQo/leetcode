// Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
//
//  
// Example 1:
//
//
// Input: n = 3
// Output: 5
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
// 	1 <= n <= 19
//
//


class Solution {
    	int[][] book;//备忘录,book[i][j]代表start=i,end=j所能生成的二叉搜索树的数量
        public int numTrees(int n) {
            if(n<=0){
            	return 0;
            }
            book=new int[n+1][n+1];
            return numTrees(1, n);
        }
        //分治--存在重复子情况--改用带备忘录形式
        private int numTrees(int start,int end){
        	if(start>=end){//空树或只含一个结点的树
        		return 1;
        	}
        	if(book[start][end]!=0){//已经计算过,直接返回结果
        		return book[start][end];
        	}
        	int count=0;
        	for(int i=start;i<=end;i++){
        		count+=numTrees(start,i-1)*numTrees(i+1, end);
        	}
        	book[start][end]=count;
        	return count;
        }
        private int numTreesTLE(int start,int end){
    	if(start>=end){//空树或只含一个结点的树
    		return 1;
    	}
    	int count=0;
    	for(int i=start;i<=end;i++){
    		count+=numTrees(start, i-1)*numTrees(i+1, end);
    	}
    	return count;
    }
}

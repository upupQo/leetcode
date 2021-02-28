// There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
//
// A province is a group of directly or indirectly connected cities and no other cities outside of the group.
//
// You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
//
// Return the total number of provinces.
//
//  
// Example 1:
//
//
// Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
// Output: 2
//
//
// Example 2:
//
//
// Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
// Output: 3
//
//
//  
// Constraints:
//
//
// 	1 <= n <= 200
// 	n == isConnected.length
// 	n == isConnected[i].length
// 	isConnected[i][j] is 1 or 0.
// 	isConnected[i][i] == 1
// 	isConnected[i][j] == isConnected[j][i]
//
//


class Solution {
 //547.无向图独立联通分量个数
    public int findCircleNum(int[][] M) {
        int parent[]=new int[M.length];//parent[i]带表编号为i的结点的父节点,最初都是自己。约定id最小的结点做为一个独立连通分量的代表
        for(int i=0;i<parent.length;i++) {
        	parent[i]=i;
        }
        for(int i=0;i<M.length;i++) {
        	for(int j=0;j<M[0].length;j++) {
        		if(M[i][j]==1) {
        			union(parent, i, j);
        		}
        	}
        }
        int count=0;
        for(int i=0;i<parent.length;i++) {
        	if(parent[i]==i) {
        		count++;
        	}
        }
        return count;
    }
    //返回index结点所在集合的根节点
    public int find(int[] indexs,int index) {
    	while(indexs[index]!=index) {
    		index=indexs[index];
    	}
    	return index;
    }
    public void union(int[] indexs,int i,int j) {
    	int parenti=find(indexs, i);
    	int parentj=find(indexs, j);
    	if(parenti<parentj) {
    		indexs[parentj]=parenti;
    	}else {//相等时相当于什么也没做
    		indexs[parenti]=parentj;
    	}
    }
}

// The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
//
// Determine the maximum amount of money the thief can rob tonight without alerting the police.
//
// Example 1:
//
//
// Input: [3,2,3,null,3,null,1]
//
//      3
//     / \
//    2   3
//     \   \ 
//      3   1
//
// Output: 7 
// Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
//
// Example 2:
//
//
// Input: [3,4,5,1,3,null,1]
//
//      3
//     / \
//    4   5
//   / \   \ 
//  1   3   1
//
// Output: 9
// Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
//


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    //337. House Robber III,不能父子结点同时偷。dfs
    public int rob(TreeNode root) {
    	int[] res=robFast(root);
    	return Math.max(res[0], res[1]);
    }
    //用robbed标记当前树的父亲是否被偷了
    private int rob(TreeNode root,boolean robbed){
    	if(root==null){
    		return 0;
    	}
    	int notrob=rob(root.left,false)+rob(root.right,false);
    	if(robbed==true){
    		return notrob;
    	}
    	int rob=rob(root.left,true)+rob(root.right,true)+root.val;
    	return Math.max(rob, notrob);
    }
    //res[0]代表偷root结点可以获取的最大值，res[1]代表不偷root结点可以获取的最大值
    public int[] robFast(TreeNode root){
    	int[] res=new int[2];
    	if(root==null){
    		return res;
    	}
    	int[] left=robFast(root.left);
    	int[] right=robFast(root.right);
    	res[0]=left[1]+right[1]+root.val;
    	res[1]=Math.max(left[0], left[1])+Math.max(right[0], right[1]);//key 不可root,左右可偷可不偷
    	return res;
    }
}

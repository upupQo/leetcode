// A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
//
// The path sum of a path is the sum of the node's values in the path.
//
// Given the root of a binary tree, return the maximum path sum of any path.
//
//  
// Example 1:
//
//
// Input: root = [1,2,3]
// Output: 6
// Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
//
//
// Example 2:
//
//
// Input: root = [-10,9,20,null,null,15,7]
// Output: 42
// Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the tree is in the range [1, 3 * 104].
// 	-1000 <= Node.val <= 1000
//
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
 //124*.以root为根的最大路径和，可以不包含也可以包含根节点二叉树最大路径和,至少包含一个结点，于是分解为两个子问题:包含根节点、不包含根节点
   //'0有歧义'
   public int maxPathSum(TreeNode root) {
       if(root==null){
    	   return 0;
       }
       //1.要根结点策略
       int max1=root.val+Math.max(maxPathSumHold(root.left), 0)+Math.max(maxPathSumHold(root.right), 0);
       //2.不要根节点策略
       if(root.left==null && root.right==null){
    	   return root.val;
       }
       int max2;
       if(root.left!=null && root.right!=null){
    	   max2=Math.max(maxPathSum(root.left),maxPathSum(root.right));
       }else if(root.left!=null){
    	   max2=maxPathSum(root.left);
       }else{
    	   max2=maxPathSum(root.right);
       }
       return Math.max(max1, max2);
   }
   //以root为根曲直向下的最大路径和 ，必须包含根节点
   private int maxPathSumHold(TreeNode root){
	   if(root==null){
		   return 0;
	   }
	   int max=Math.max(Math.max(maxPathSumHold(root.left), maxPathSumHold(root.right)),0);
	   return root.val+max;
   }
}

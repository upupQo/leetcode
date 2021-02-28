// Given a binary tree, determine if it is height-balanced.
//
// For this problem, a height-balanced binary tree is defined as:
//
//
// a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
//
//
//  
// Example 1:
//
//
// Input: root = [3,9,20,null,null,15,7]
// Output: true
//
//
// Example 2:
//
//
// Input: root = [1,2,2,3,3,null,null,4,4]
// Output: false
//
//
// Example 3:
//
//
// Input: root = []
// Output: true
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the tree is in the range [0, 5000].
// 	-104 <= Node.val <= 104
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
    public boolean isBalanced(TreeNode root) {
        return isBalancedImp(root)==-1?false:true;
    }
    //若是平衡的，返回该树的高度，若不是平衡的，返回-1.
    //主要是因为该题涉及子问题的两个信息(boolean 子树是否是平衡的，int 子树的高度)，这样会导致重复访问；实际上可以统一处理。
    private int isBalancedImp(TreeNode root){
    	if(root==null){
    		return 0;
    	}
    	int leftHeight=isBalancedImp(root.left);
    	if(leftHeight==-1){
    		return -1;
    	}
    	int rightHeight=isBalancedImp(root.right);
    	if(rightHeight==-1){
    		return -1;
    	}
    	if(Math.abs(leftHeight-rightHeight)>1){
    		return -1;
    	}
    	return Math.max(leftHeight, rightHeight)+1;
    }
}

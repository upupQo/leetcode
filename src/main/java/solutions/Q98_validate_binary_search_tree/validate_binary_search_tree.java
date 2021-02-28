// Given the root of a binary tree, determine if it is a valid binary search tree (BST).
//
// A valid BST is defined as follows:
//
//
// 	The left subtree of a node contains only nodes with keys less than the node's key.
// 	The right subtree of a node contains only nodes with keys greater than the node's key.
// 	Both the left and right subtrees must also be binary search trees.
//
//
//  
// Example 1:
//
//
// Input: root = [2,1,3]
// Output: true
//
//
// Example 2:
//
//
// Input: root = [5,1,4,null,null,3,6]
// Output: false
// Explanation: The root node's value is 5 but its right child's value is 4.
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the tree is in the range [1, 104].
// 	-231 <= Node.val <= 231 - 1
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
 public boolean isValidBST(TreeNode root) {
       return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    //以root为根的树的所有结点值应该∈[min,max]，递归利用BST的性质
    private boolean isValidBST(TreeNode root,long min,long max){
    	if(root==null){
    		return true;
    	}
    	if(root.val<min || root.val>max){
    		return false;
    	}
    	return isValidBST(root.left, min, (long)root.val-1) && isValidBST(root.right, (long)root.val+1, max);
    }
}

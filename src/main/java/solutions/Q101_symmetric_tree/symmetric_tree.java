// Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
//
//  
// Example 1:
//
//
// Input: root = [1,2,2,3,4,4,3]
// Output: true
//
//
// Example 2:
//
//
// Input: root = [1,2,2,null,3,null,3]
// Output: false
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the tree is in the range [1, 1000].
// 	-100 <= Node.val <= 100
//
//
//  
// Follow up: Could you solve it both recursively and iteratively?


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
    //101.*判断一颗二叉树是否是镜像的
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
        	return true;
        }
        return isSymmetric(root.left, root.right);
    }
    //以root1为根的二叉树与以root2为根的二叉树是否是镜像二叉树。
    private boolean isSymmetric(TreeNode root1,TreeNode root2){
    	if(root1==null && root2==null){
    		return true;
    	}
    	if(root1==null || root2==null || root1.val!=root2.val){
    		return false;
    	}
    	return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
    }
}

// Given the root of a binary tree, flatten the tree into a "linked list":
//
//
// 	The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
// 	The "linked list" should be in the same order as a pre-order traversal of the binary tree.
//
//
//  
// Example 1:
//
//
// Input: root = [1,2,5,3,4,null,6]
// Output: [1,null,2,null,3,null,4,null,5,null,6]
//
//
// Example 2:
//
//
// Input: root = []
// Output: []
//
//
// Example 3:
//
//
// Input: root = [0]
// Output: [0]
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the tree is in the range [0, 2000].
// 	-100 <= Node.val <= 100
//
//
//  
// Follow up: Can you flatten the tree in-place (with O(1) extra space)?


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void flatten(TreeNode root) {
        if(root==null){
        	return;
        }
        flattenImp(root);
        root.left=null;
    }
    //把以root为根的树前序化为闭环链
    public void flattenImp(TreeNode root) {
        if(root==null){
        	return;
        }
        if(root.left==null && root.right==null){
        	root.left=root;//自环，key
        	return;
        }
        flattenImp(root.left);
        flattenImp(root.right);
        TreeNode root2;
        if(root.left!=null && root.right!=null){//由于先序所以先把左右结起来
        	merge(root.left, root.right);
        	root2=root.left;
        }else if(root.left!=null){//右子树为空
        	root2=root.left;
        }else{//左子树为空,右子树非空
        	root2=root.right;
        }
        root.left=root;//根节点形成闭环
        root.right=null;
        merge(root, root2);
    }
    //把环链root2接到环链root1上形成一个新的大环链
    public void merge(TreeNode root1,TreeNode root2){
    	TreeNode rightEnd1=root1.left;
    	TreeNode rightEnd2=root2.left;
    	rightEnd1.right=root2;
    	root2.left=null;
    	root1.left=rightEnd2;
    }
}

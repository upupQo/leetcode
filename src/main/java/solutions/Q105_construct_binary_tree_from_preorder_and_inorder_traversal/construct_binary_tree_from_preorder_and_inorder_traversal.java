// Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
//
//  
// Example 1:
//
//
// Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
// Output: [3,9,20,null,null,15,7]
//
//
// Example 2:
//
//
// Input: preorder = [-1], inorder = [-1]
// Output: [-1]
//
//
//  
// Constraints:
//
//
// 	1 <= preorder.length <= 3000
// 	inorder.length == preorder.length
// 	-3000 <= preorder[i], inorder[i] <= 3000
// 	preorder and inorder consist of unique values.
// 	Each value of inorder also appears in preorder.
// 	preorder is guaranteed to be the preorder traversal of the tree.
// 	inorder is guaranteed to be the inorder traversal of the tree.
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }
    public TreeNode build(int[] preorder, int[] inorder,int preStart,int preEnd,int inStart,int inEnd){
    	if(preStart>preEnd){
    		return null;
    	}
    	//顺序查找preorder[preStart]在inorder中对应的索引
    	int index=inStart;
    	while(inorder[index]!=preorder[preStart]){
    		index++;
    	}
    	TreeNode root=new TreeNode(inorder[index]);
    	root.left=build(preorder, inorder, preStart+1, preStart+index-inStart, inStart, index-1);
    	root.right=build(preorder, inorder, preStart+index-inStart+1, preEnd, index+1, inEnd);
    	return root;
    }
}

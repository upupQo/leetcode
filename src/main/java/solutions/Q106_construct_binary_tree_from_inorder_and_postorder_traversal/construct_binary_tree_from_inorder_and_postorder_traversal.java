// Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
//
//  
// Example 1:
//
//
// Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
// Output: [3,9,20,null,null,15,7]
//
//
// Example 2:
//
//
// Input: inorder = [-1], postorder = [-1]
// Output: []
//
//
//  
// Constraints:
//
//
// 	1 <= inorder.length <= 3000
// 	postorder.length == inorder.length
// 	-3000 <= inorder[i], postorder[i] <= 3000
// 	inorder and postorder consist of unique values.
// 	Each value of postorder also appears in inorder.
// 	inorder is guaranteed to be the inorder traversal of the tree.
// 	postorder is guaranteed to be the postorder traversal of the tree.
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, postorder, 0, inorder.length-1, 0, postorder.length-1);
    }
    //用inorder[inStart,inEnd] 和postorder[posStart,posEnd]建立一颗二叉树
    public TreeNode build(int[] inorder, int[] postorder,int inStart,int inEnd,int posStart,int posEnd){
    	if(inStart>inEnd){
    		return null;
    	}
    	//顺序查找postorder[posEnd]在inorder中对应的索引
    	int index=inStart;
    	while(inorder[index]!=postorder[posEnd]){
    		index++;
    	}
    	TreeNode root=new TreeNode(inorder[index]);
    	root.left=build(inorder, postorder, inStart, index-1, posStart,posStart+index-inStart-1);
    	root.right=build(inorder, postorder, index+1, inEnd, posStart+index-inStart, posEnd-1);
    	return root;
    }
}

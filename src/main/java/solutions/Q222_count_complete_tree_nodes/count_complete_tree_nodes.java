// Given the root of a complete binary tree, return the number of the nodes in the tree.
//
// According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
//
//  
// Example 1:
//
//
// Input: root = [1,2,3,4,5,6]
// Output: 6
//
//
// Example 2:
//
//
// Input: root = []
// Output: 0
//
//
// Example 3:
//
//
// Input: root = [1]
// Output: 1
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the tree is in the range [0, 5 * 104].
// 	0 <= Node.val <= 5 * 104
// 	The tree is guaranteed to be complete.
//
//
//  
// Follow up: Traversing the tree to count the number of nodes in the tree is an easy solution but with O(n) complexity. Could you find a faster algorithm?


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
    //222.*完全二叉树的节点数:递归1+count(roo.left)+count(root.right)
    public int countNodes(TreeNode root) {
        int count=0;
        while(root!=null){
        	//1.获取左子树高度
        	int leftDepth=0;//左子树的高度
        	TreeNode node=root.left;
        	while(node!=null){
        		leftDepth++;
        		node=node.left;
        	}
        	//2.获取右子树高度
        	int rightDepth=0;//右子树的高度
        	node=root.right;
        	while(node!=null){
        		rightDepth++;
        		node=node.left;//notice同上
        	}
        	//3.判定
        	if(leftDepth==rightDepth){//左子树高度与右子树高度相等，那么左子树肯定是满二叉树，右子树可能满也可能不满
        		count+=1<<leftDepth;//左子树上结点个数+当前这个root结点
        		root=root.right;
        	}else{//leftDepth>rightDepth,右子树是满二叉树，左子树可能满也可能不满
        		count+=1<<rightDepth;
        		root=root.left;
        	}
        }
        return count;
    }
}

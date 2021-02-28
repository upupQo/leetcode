// Given a binary tree, find its minimum depth.
//
// The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
//
// Note: A leaf is a node with no children.
//
//  
// Example 1:
//
//
// Input: root = [3,9,20,null,null,15,7]
// Output: 2
//
//
// Example 2:
//
//
// Input: root = [2,null,3,null,4,null,5,null,6]
// Output: 5
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the tree is in the range [0, 105].
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
    //111.以root为根的最小路径深度(根节点到页结点的最短距离)
    public int minDepth(TreeNode root){
        if(root==null){
            return 0;
        }
        return minDepthInternal(root);
    }
    public int minDepthInternal(TreeNode root) {
       if(root.left==null && root.right==null){//到达叶子节点
            return 1;
        }
        //对于以root为根的树：树的最小深度为
        //1.左右子树均不为空：左右子树的最小深度的最小值+1
        //2.左空右不空：右子树的最小深度+1
        //3.右空左不空：左子树的最小深度+1
        //4.左右均为空，该结点就是叶子结点，返回1
        //notice:深度！=高度
        if(root.left!=null && root.right!=null){
            return Math.min(minDepthInternal(root.left),minDepthInternal(root.right))+1;
        }else if(root.left!=null){
           return minDepthInternal(root.left)+1;
       }else{
           return minDepthInternal(root.right)+1;
       }
    }
}

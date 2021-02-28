// Given the root of a binary tree, return the postorder traversal of its nodes' values.
//
//  
// Example 1:
//
//
// Input: root = [1,null,2,3]
// Output: [3,2,1]
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
// Input: root = [1]
// Output: [1]
//
//
// Example 4:
//
//
// Input: root = [1,2]
// Output: [2,1]
//
//
// Example 5:
//
//
// Input: root = [1,null,2]
// Output: [2,1]
//
//
//  
// Constraints:
//
//
// 	The number of the nodes in the tree is in the range [0, 100].
// 	-100 <= Node.val <= 100
//
//
//  
//
// Follow up:
//
// Recursive solution is trivial, could you do it iteratively?
//
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
    //145. 二叉树非递归后序遍历--借助栈
    //reserve(根---右子树---左子树)==>左子树--右子树--根
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list=new ArrayList<Integer>();
        if(root==null){
        	return list;
        }
        Stack<TreeNode> stack=new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
        	TreeNode cur=stack.pop();
        	list.add(cur.val);
        	//先压左孩子再压右孩子--
        	if(cur.left!=null){
        		stack.push(cur.left);
        	}
        	if(cur.right!=null){
        		stack.push(cur.right);
        	}
        }
        Collections.reverse(list);//key
        return list;
    }
}

// Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
//
//  
// Example 1:
//
//
// Input: root = [3,9,20,null,null,15,7]
// Output: [[3],[9,20],[15,7]]
//
//
// Example 2:
//
//
// Input: root = [1]
// Output: [[1]]
//
//
// Example 3:
//
//
// Input: root = []
// Output: []
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the tree is in the range [0, 2000].
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
public List<List<Integer>> levelOrder(TreeNode root) {
	   List<List<Integer>> res=new ArrayList<List<Integer>>();
	   if(root==null){
		   return res;
	   }
	   Deque<TreeNode> deque=new LinkedList<TreeNode>();
	   deque.addFirst(root);
	   while(!deque.isEmpty()){
		   ArrayList<Integer> layer=new ArrayList<Integer>();
		   int size=deque.size();//当前层元素个数
		   while(size>0){
			   TreeNode cur=deque.pollLast();
			   if(cur.left!=null){
				   deque.addFirst(cur.left);
			   }
			   if(cur.right!=null){
				   deque.addFirst(cur.right);
			   }
			   layer.add(cur.val);
			   size--;
		   }
		   res.add(layer);
	   }
	   return res;
   }
}

// Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
//
//  
// Example 1:
//
//
// Input: root = [3,9,20,null,null,15,7]
// Output: [[3],[20,9],[15,7]]
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
// 	-100 <= Node.val <= 100
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
//103.ZigZag层次遍历--设想已经把某层平铺到了deque中，如何取元素才能把下一层平铺到deque中
   public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	   List<List<Integer>> res=new ArrayList<List<Integer>>();
	   if(root==null){
		   return res;
	   }
	   Deque<TreeNode> deque=new LinkedList<TreeNode>();
	   deque.addFirst(root);
	   boolean flag=true;//当前层遍历方向。
	   while(!deque.isEmpty()){
		   int size=deque.size();//当前遍历层元素个数
		   ArrayList<Integer> layer=new ArrayList<Integer>();
		   if(flag){//当前层从左往右-->下一层从右往左遍历
			   while(size>0){//从队尾取，先把左孩子放到队首，再把右孩子放到队首。
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
		   }else{//当前层从右往左遍历
			   while(size>0){//从队首取，先把右孩子放到队尾，再把左孩子放到队尾。
				   TreeNode cur=deque.pollFirst();
				   if(cur.right!=null){
					   deque.addLast(cur.right);
				   }
				   if(cur.left!=null){
					   deque.addLast(cur.left);
				   }
				   layer.add(cur.val);
				   size--;
			   }
		   }
		   res.add(layer);
		   flag=!flag;//notice,下一层与当前层遍历方向相反
	   }
	   return res;
   }
}

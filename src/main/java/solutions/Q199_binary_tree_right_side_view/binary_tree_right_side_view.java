// Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
//
//  
// Example 1:
//
//
// Input: root = [1,2,3,null,5,null,4]
// Output: [1,3,4]
//
//
// Example 2:
//
//
// Input: root = [1,null,3]
// Output: [1,3]
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
// 	The number of nodes in the tree is in the range [0, 100].
// 	-100 <= Node.val <= 100
//
//


class Solution {
   public List<Integer> rightSideView(TreeNode root) {
	   List<Integer> rightNodes=new ArrayList<Integer>();
	   Queue<TreeNode> queue=new LinkedList<TreeNode>();
	   if(root==null){
		   return rightNodes;
	   }
	   queue.add(root);
	   TreeNode cur=new TreeNode(-1);
	   while(!queue.isEmpty()){
		   int size=queue.size();
		   while(size>0){
			   cur=queue.poll();
			   if(cur.left!=null){
				   queue.add(cur.left);
			   }
			   if(cur.right!=null){
				   queue.add(cur.right);
			   }
			   size--;
		   }
		   //一轮结束，cur保存的是这层最右结点
		   rightNodes.add(cur.val);
	   }
	   return rightNodes;
   }
}

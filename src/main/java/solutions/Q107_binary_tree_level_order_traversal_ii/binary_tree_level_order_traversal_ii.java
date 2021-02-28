// Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).
//
//  
// Example 1:
//
//
// Input: root = [3,9,20,null,null,15,7]
// Output: [[15,7],[9,20],[3]]
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


class Solution {
  //107.自底向上的层次遍历--先自上而下进行层次遍历，再反转
   public List<List<Integer>> levelOrderBottom(TreeNode root) {
	   List<List<Integer>> res=new ArrayList<List<Integer>>();
	   if(root==null){
		   return res;
	   }
	   Deque<TreeNode> deque=new LinkedList<TreeNode>();
	   deque.addFirst(root);
	   while(!deque.isEmpty()){
		   ArrayList<Integer> layer=new ArrayList<Integer>();
		   int size=deque.size();//当前层元素个数
		   while(size>0){//从队尾取，往对头压，先压左孩子，再压右孩子
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
	   List<Integer> temp;
	   for(int i=0;i<res.size()/2;i++){
		   temp=res.get(i);
		   res.set(i, res.get(res.size()-1-i));
		   res.set(res.size()-1-i,temp);
	   }
	   return res;
   }
}

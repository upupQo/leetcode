// Given a binary tree
//
//
// struct Node {
//   int val;
//   Node *left;
//   Node *right;
//   Node *next;
// }
//
//
// Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
//
// Initially, all next pointers are set to NULL.
//
//  
//
// Follow up:
//
//
// 	You may only use constant extra space.
// 	Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
//
//
//  
// Example 1:
//
//
//
//
// Input: root = [1,2,3,4,5,null,7]
// Output: [1,#,2,3,#,4,5,7,#]
// Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the given tree is less than 6000.
// 	-100 <= node.val <= 100
//
//


/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
 public void connect(TreeLinkNode root) {
	   TreeLinkNode cur=root;//已经填充层的当前结点，实现下一层的填充
	   TreeLinkNode dummy=dummy=new TreeLinkNode(-1),tail=dummy;//下一层链的虚拟头结点、尾结点。skill
	   while(cur!=null){
		   if(cur.left!=null){
			   tail.next=cur.left;
			   tail=tail.next;
		   }
		   if(cur.right!=null){
			   tail.next=cur.right;
			   tail=tail.next;
		   }
		   cur=cur.next;
		   if(cur==null){//key
			   cur=dummy.next;//已经填完了一层，切换到下一层填下下层
			   dummy=new TreeLinkNode(-1);//虚拟头也可以不重置
			   tail=dummy;
		   }
	   }
   }
}

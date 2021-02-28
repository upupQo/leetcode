// Given a binary tree, return all root-to-leaf paths.
//
// Note: A leaf is a node with no children.
//
// Example:
//
//
// Input:
//
//    1
//  /   \
// 2     3
//  \
//   5
//
// Output: ["1->2->5", "1->3"]
//
// Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
	   	private List<String> paths;
	    public List<String> binaryTreePaths(TreeNode root) {
	        paths=new ArrayList<String>();
	        constructPath(root, new ArrayList<Integer>());
	        return paths;
	    }
	    public void constructPath(TreeNode root,List<Integer> vals){
	    	if(root==null){
	    		return;
	    	}
	    	//1.到达叶节点,构造路径
	    	if(root.left==null && root.right==null){
	    		StringBuilder sb=new StringBuilder();
	    		for(int i=0;i<vals.size();i++){
	    			sb.append(vals.get(i)+"->");
	    		}
	    		sb.append(root.val);
	    		paths.add(sb.toString());
	    		return;
	    	}
	    	//2.非叶节点
	    	vals.add(root.val);
	    	constructPath(root.left, vals);
	    	constructPath(root.right, vals);
	    	vals.remove(vals.size()-1);//notice 回溯时保证引用遍历状态一致
	    }
}

// Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where each path's sum equals targetSum.
//
// A leaf is a node with no children.
//
//  
// Example 1:
//
//
// Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
// Output: [[5,4,11,2],[5,8,4,5]]
//
//
// Example 2:
//
//
// Input: root = [1,2,3], targetSum = 5
// Output: []
//
//
// Example 3:
//
//
// Input: root = [1,2], targetSum = 0
// Output: []
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the tree is in the range [0, 5000].
// 	-1000 <= Node.val <= 1000
// 	-1000 <= targetSum <= 1000
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
	    public List<List<Integer>> pathSum(TreeNode root, int sum) {
	    	List<List<Integer>> paths=new ArrayList<List<Integer>>();
	    	List<Integer> currentList=new ArrayList<Integer>();
	    	pathSum(root, sum, currentList, paths);
	    	return paths;
	    }
	    //以root为根，找和为sum的路径和
	    private void pathSum(TreeNode root,int sum,List<Integer> currentList,List<List<Integer>> paths){
	    	if(root==null){
	    		return;
	    	}
	    	if(root.left==null && root.right==null){//到达叶子，检查路径和
	    		if(root.val==sum){//有效路径
	    			currentList.add(root.val);
	    			paths.add(new ArrayList<Integer>(currentList));
	    			currentList.remove(currentList.size()-1);//回溯
	    			return;
	    		}
	    	}else{
	    		currentList.add(root.val);
	    		pathSum(root.left, sum-root.val, currentList, paths);
	    		pathSum(root.right, sum-root.val, currentList, paths);
	    		currentList.remove(currentList.size()-1);//回溯
	    	}
	    }
	}

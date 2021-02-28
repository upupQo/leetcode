// Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
//
//  
// Example 1:
//
//
// Input: root = [5,3,6,2,4,null,7], k = 9
// Output: true
//
//
// Example 2:
//
//
// Input: root = [5,3,6,2,4,null,7], k = 28
// Output: false
//
//
// Example 3:
//
//
// Input: root = [2,1,3], k = 4
// Output: true
//
//
// Example 4:
//
//
// Input: root = [2,1,3], k = 1
// Output: false
//
//
// Example 5:
//
//
// Input: root = [2,1,3], k = 3
// Output: true
//
//
//  
// Constraints:
//
//
// 	The number of nodes in the tree is in the range [1, 104].
// 	-104 <= Node.val <= 104
// 	root is guaranteed to be a valid binary search tree.
// 	-105 <= k <= 105
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
 public boolean findTarget(TreeNode root, int k) {
        ArrayList<Integer> list=inOrder(root);//对二叉搜索树进行中序遍历，得到的将是一个有序序列
        return twoSum(list, k);
    }
    //在有序list中找和为target的两个数
    public boolean twoSum(ArrayList<Integer> list,int target){
    	if(list==null || list.size()<2){
    		return false;
    	}
    	int low=0,high=list.size()-1;
        while(low<high){
        	if(list.get(low)+list.get(high)==target){
        		return true;
        	}else if(list.get(low)+list.get(high)>target){
        		high--;
        	}else{
        		low++;
        	}
        }
        return false;
    }
    //非递归中序遍历二叉树,用root表示一个从未经过过的树
    public ArrayList<Integer> inOrder(TreeNode root){
    	ArrayList<Integer> res=new ArrayList<Integer>();
    	if(root==null){
    		return res;
    	}
    	Stack<TreeNode> stackHelp=new Stack<TreeNode>();//辅助栈
    	TreeNode cur;//对该结点进行访问visit
    	while(!(root==null && stackHelp.isEmpty())){
    		if(root!=null){//压左边界
    			while(root!=null){
    				stackHelp.push(root);
    				root=root.left;
    			}
    		}else{
    			cur=stackHelp.pop();
    			res.add(cur.val);
    			root=cur.right;
    		}
    	}
    	return res;
    }
}

// Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
//
//  
// Example 1:
//
//
// Input: n = 3
// Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
//
//
// Example 2:
//
//
// Input: n = 1
// Output: [[1]]
//
//
//  
// Constraints:
//
//
// 	1 <= n <= 8
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
    public class Solution {
        public List<TreeNode> generateTrees(int n) {
            if(n<=0){
        		return new ArrayList<TreeNode>();//为了ac，本来只返回一颗空树也应该是可以的
        	}
        	List<TreeNode> res=generateTrees(1, n);
        	return res;
        }
        //生成区间为[start,end]的所有二叉排序树
        private List<TreeNode> generateTrees(int start,int end){
        	List<TreeNode> list=new ArrayList<TreeNode>();
        	if(start>end){
        		list.add(null);
        		return list;//仅能生成一个null结点
        	}
        	if(start==end){
        		list.add(new TreeNode(start));
        		return list;
        	}
        	for(int i=start;i<=end;i++){
        		List<TreeNode> left=generateTrees(start, i-1);//[start,i-1]生成的所有二叉排序树
        		List<TreeNode> right=generateTrees(i+1, end);//[i+1,end]生成的所有二叉排序树
        		TreeNode head;
        		//迭代形成所有组合
        		for(int j=0;j<left.size();j++){
        			for(int k=0;k<right.size();k++){
        				head=new TreeNode(i);
        				head.left=copyTree(left.get(j));
        				head.right=copyTree(right.get(k));
        				list.add(head);
        			}
        		}
        	}
        	return list;
        }
        //复制一颗二叉树
        private TreeNode copyTree(TreeNode head){
        	if(head==null){
        		return null;
        	}
        	TreeNode newHead=new TreeNode(head.val);
        	newHead.left=copyTree(head.left);
        	newHead.right=copyTree(head.right);
        	return newHead;
        }
    }

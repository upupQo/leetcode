// Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
//
// The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
//
// It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
//
//  
// Example 1:
//
//
// Input: candidates = [2,3,6,7], target = 7
// Output: [[2,2,3],[7]]
// Explanation:
// 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
// 7 is a candidate, and 7 = 7.
// These are the only two combinations.
//
//
// Example 2:
//
//
// Input: candidates = [2,3,5], target = 8
// Output: [[2,2,2,2],[2,3,3],[3,5]]
//
//
// Example 3:
//
//
// Input: candidates = [2], target = 1
// Output: []
//
//
// Example 4:
//
//
// Input: candidates = [1], target = 1
// Output: [[1]]
//
//
// Example 5:
//
//
// Input: candidates = [1], target = 2
// Output: [[1,1]]
//
//
//  
// Constraints:
//
//
// 	1 <= candidates.length <= 30
// 	1 <= candidates[i] <= 200
// 	All elements of candidates are distinct.
// 	1 <= target <= 500
//
//


class Solution {
    //39.无重复元素正数数组子集和,但是每个元素可以使用无数次:对每一个位置i枚举可以出现的次数:0,1,2...
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	List<List<Integer>> res=new ArrayList<List<Integer>>();
    	List<Integer> currentList=new ArrayList<Integer>();
    	combinationSum(candidates, target, 0, 0, currentList, res);
    	return res;
    }
    public void combinationSum(int[] arr,int target,int index,int currentSum,List<Integer> currentList,List<List<Integer>> res){
    	if(currentSum==target){
    		res.add(new ArrayList<Integer>(currentList));
    		return;
    	}
    	//由于正数数组，所以一旦之前和已经超过target
    	if(currentSum>target || index==arr.length){
    		return;
    	}
    	for(int count=0;arr[index]*count+currentSum<=target;count++){//枚举arr[i]可以使用可次数
    		for(int j=0;j<count;j++){
    			currentList.add(arr[index]);
    		}
    		combinationSum(arr, target, index+1, currentSum+arr[index]*count, currentList, res);
    		for(int j=0;j<count;j++){
    			currentList.remove(currentList.size()-1);
    		}
    	}
    }
}

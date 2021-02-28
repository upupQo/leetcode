// Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
//
// Each number in candidates may only be used once in the combination.
//
// Note: The solution set must not contain duplicate combinations.
//
//  
// Example 1:
//
//
// Input: candidates = [10,1,2,7,6,1,5], target = 8
// Output: 
// [
// [1,1,6],
// [1,2,5],
// [1,7],
// [2,6]
// ]
//
//
// Example 2:
//
//
// Input: candidates = [2,5,2,1,2], target = 5
// Output: 
// [
// [1,2,2],
// [5]
// ]
//
//
//  
// Constraints:
//
//
// 	1 <= candidates.length <= 100
// 	1 <= candidates[i] <= 50
// 	1 <= target <= 30
//
//


class Solution {
    //40.含重复元素正数数组子集和,但是每个元素在一个集合只能使用一次。由于含有重复元素，考虑每个位置用或不用无法直接去重,于是换一种思路
    //集合I的所有子集=含i1的所有子集 I1  U 
    //			  不含i1,含i2的所有子集 I2 U 
    //           不含i1且不含i2,但含i3的所有子集I3...。
    //若i2=i1,则显然I2是I1的子集(所有出现i2的地方都可以用i1代替)
    public List<List<Integer>> combinationSum2(int[] candidates, int target){
      	List<List<Integer>> res=new ArrayList<List<Integer>>();
    	List<Integer> currentList=new ArrayList<Integer>();
        Arrays.sort(candidates);
        combinationSumII(candidates, target, 0, 0, currentList, res);
        return res;
    }
	//不含[0,start-1],含start的所有子集
	private void combinationSumII(int[] arr,int target,int start,int currentSum,List<Integer> currentList,List<List<Integer>> res){
		if(currentSum==target){
			res.add(new ArrayList<Integer>(currentList));
			return;
		}
		if(currentSum>target || start==arr.length){
			return;
		}
		for(int index=start;index<arr.length;index++){
			currentList.add(arr[index]);
			combinationSumII(arr, target, index+1, currentSum+arr[index], currentList, res);
			currentList.remove(currentList.size()-1);
			while(index+1<arr.length && arr[index+1]==arr[index]){
				index++;
			}
		}
	}
}

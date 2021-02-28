// Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
//
// Notice that the solution set must not contain duplicate triplets.
//
//  
// Example 1:
// Input: nums = [-1,0,1,2,-1,-4]
// Output: [[-1,-1,2],[-1,0,1]]
// Example 2:
// Input: nums = []
// Output: []
// Example 3:
// Input: nums = [0]
// Output: []
//
//  
// Constraints:
//
//
// 	0 <= nums.length <= 3000
// 	-105 <= nums[i] <= 105
//
//


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>>  res=new ArrayList<List<Integer>>();
    	if(nums==null || nums.length<3){
    		return res;
    	}
    	//对输入排序nlogn
    	Arrays.sort(nums);
    	int indexA=0;
    	while(indexA<nums.length-2){//a的可枚举位置为[0,nums.length-3]
    		List<List<Integer>> pairs=twoSum(nums, indexA+1,nums.length-1 ,0-nums[indexA]);
    		if(pairs.size()>0){
    			for(int i=0;i<pairs.size();i++){
    				pairs.get(i).add(nums[indexA]);
    				res.add(pairs.get(i));
    			}
    		}
    		indexA++;
            while(indexA<nums.length-2 && nums[indexA]==nums[indexA-1]){//去重
    			indexA++;
    		}
    	}
    	return res;
    }
    //在有序数组nums的[start,end]区间中找两个数，和为target,返回两个数字具体值(返回所有解,去重)
    public List<List<Integer>> twoSum(int[] nums,int start,int end,int target){
    	List<List<Integer>> res=new ArrayList<List<Integer>>();
    	ArrayList<Integer> pair;
    	while(start<end){
    		if(nums[start]+nums[end]==target){
    			pair=new ArrayList<Integer>();
    			pair.add(nums[start]);
    			pair.add(nums[end]);
    			res.add(pair);
    			start++;
    			end--;
    			//去重
    			while(start<end && nums[start]==nums[start-1]){
    				start++;
    			}
    			while(end>start && nums[end]==nums[end+1]){
    				end--;
    			}
    		}else if(nums[start]+nums[end]>target){
    			end--;
    		}else{
    			start++;
    		}
    	}
    	return res;
    }
}

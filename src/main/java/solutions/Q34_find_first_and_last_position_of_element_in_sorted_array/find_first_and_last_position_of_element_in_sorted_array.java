// Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
//
// If target is not found in the array, return [-1, -1].
//
// Follow up: Could you write an algorithm with O(log n) runtime complexity?
//
//  
// Example 1:
// Input: nums = [5,7,7,8,8,10], target = 8
// Output: [3,4]
// Example 2:
// Input: nums = [5,7,7,8,8,10], target = 6
// Output: [-1,-1]
// Example 3:
// Input: nums = [], target = 0
// Output: [-1,-1]
//
//  
// Constraints:
//
//
// 	0 <= nums.length <= 105
// 	-109 <= nums[i] <= 109
// 	nums is a non-decreasing array.
// 	-109 <= target <= 109
//
//


class Solution {
    public int[] searchRange(int[] nums, int target) {
    	int[] res={-1,-1};
    	if(nums.length==0){
    		return res;
    	}
        //1.在nums[0,n-1]找target第一次出现的位置
    	int start=0,end=nums.length-1;
    	int middle;
    	while(start<end){//设[satr,end]维护一个有解区间，当还有>=两个元素时
    		middle=(start+end)/2;
    		if(nums[middle]<target){
    			start=middle+1;
    		}else{//nums[middle]>=target
    			end=middle;
    		}
    	}
    	//没出现过
    	if(nums[start]!=target){
    		return res;
    	}
    	res[0]=start;
    	//2.在[start+1,n-1]中找最后一次出现的位置
        start=start+1;
    	end=nums.length-1;
    	while(start<end){
    		middle=(start+end+1)/2;//保存下中位数skill
    		if(nums[middle]>target){
    			end=middle-1;
    		}else{
    			start=middle;
    		}
    	}
        if(start==nums.length || nums[start]!=target){//只出现过一次notice
    		res[1]=res[0];
    	}else{
            res[1]=start;
        }
    	return res;
    }
}

// Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
//
//  
// Example 1:
// Input: nums = [1,3,5,6], target = 5
// Output: 2
// Example 2:
// Input: nums = [1,3,5,6], target = 2
// Output: 1
// Example 3:
// Input: nums = [1,3,5,6], target = 7
// Output: 4
// Example 4:
// Input: nums = [1,3,5,6], target = 0
// Output: 0
// Example 5:
// Input: nums = [1], target = 0
// Output: 0
//
//  
// Constraints:
//
//
// 	1 <= nums.length <= 104
// 	-104 <= nums[i] <= 104
// 	nums contains distinct values sorted in ascending order.
// 	-104 <= target <= 104
//
//


class Solution {
        //35.寻找插入位置。等价于在有序数组中找第一个大于等于target的位置
    public int searchInsert(int[] nums, int target) {
    	if(nums.length==0 || nums[0]>=target){
    		return 0;
    	}
    	if(target>nums[nums.length-1]){//大于等于target的数不存在
    		return nums.length;
    	}
        int start=0,end=nums.length-1;
        int middle;
        while(start<end){
        	middle=(start+end)/2;
        	if(nums[middle]>=target){
        		end=middle;
        	}else{
        		start=middle+1;
        	}
        }
        return start;
    }
}

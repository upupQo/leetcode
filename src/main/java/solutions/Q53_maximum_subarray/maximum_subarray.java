// Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
//
//  
// Example 1:
//
//
// Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
// Output: 6
// Explanation: [4,-1,2,1] has the largest sum = 6.
//
//
// Example 2:
//
//
// Input: nums = [1]
// Output: 1
//
//
// Example 3:
//
//
// Input: nums = [0]
// Output: 0
//
//
// Example 4:
//
//
// Input: nums = [-1]
// Output: -1
//
//
// Example 5:
//
//
// Input: nums = [-100000]
// Output: -100000
//
//
//  
// Constraints:
//
//
// 	1 <= nums.length <= 3 * 104
// 	-105 <= nums[i] <= 105
//
//
//  
// Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.


class Solution {
    //设当前在i位置,pre记录以arr[i-1]为结尾的最大子数组和，则以arr[i]为结尾的最大子数组和=arr[i]+max(0,pre);--最简单的dp思想
    public int maxSubArray(int[] nums) {
        int pre=0;
        int res=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
        	pre=nums[i]+Math.max(pre, 0);
        	res=Math.max(res, pre);
        }
        return res;
    }
}

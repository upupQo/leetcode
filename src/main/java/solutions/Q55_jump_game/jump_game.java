// Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
//
// Each element in the array represents your maximum jump length at that position.
//
// Determine if you are able to reach the last index.
//
//  
// Example 1:
//
//
// Input: nums = [2,3,1,1,4]
// Output: true
// Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
//
//
// Example 2:
//
//
// Input: nums = [3,2,1,0,4]
// Output: false
// Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
//
//
//  
// Constraints:
//
//
// 	1 <= nums.length <= 3 * 104
// 	0 <= nums[i] <= 105
//
//


class Solution {
    public boolean canJump(int[] nums) {
        //用[left,right]维护一个目前可达区间
        int left=0;
        int right=0;
        int rightest;//贪心key,以[left,right]为出发点可以达到的最远点
        while(right<nums.length-1){//可达区间还没覆盖最后一个元素
        	rightest=right;
        	while(left<=right){
        		rightest=Math.max(rightest, left+nums[left]);
                left++;
        	}
        	if(rightest==right){//跳不到了
        		return false;
        	}else{
        		right=rightest;
        	}
        }
        return true;
    }
}

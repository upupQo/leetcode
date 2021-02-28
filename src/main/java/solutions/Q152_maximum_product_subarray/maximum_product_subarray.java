// Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
//
// Example 1:
//
//
// Input: [2,3,-2,4]
// Output: 6
// Explanation: [2,3] has the largest product 6.
//
//
// Example 2:
//
//
// Input: [-2,0,-1]
// Output: 0
// Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
//


class Solution {
    //64.最大乘积子数组:dp[i][0]:以arr[i]为结尾的最大子数组乘积,dp[i][1]:以arr[i]为结尾的最小子数组乘积
    //若nums[i]==0,则dp[i][0]=0,dp[i][1]=0，否则
    //dp[i][0]=max(nums[i]*dp[i-1][0],nums[i]*dp[i-1][1],nums[i]),考虑到preMax可能为0
    //dp[i][1]=min(nums[i]*dp[i-1][0],nums[i]*dp[i-1][1],nums[i]),考虑到preMin可能为0
    public int maxProduct(int[] nums) {
    	if(nums.length==0){
    		return 0;
    	}
        int min=nums[0];
        int max=nums[0];
        int maxProduct=nums[0];
        for(int i=1;i<nums.length;i++){
        	if(nums[i]==0){
        		max=0;
        		min=0;
        	}else{
        		int temp=Math.max(Math.max(nums[i]*max, nums[i]*min), nums[i]);
        		min=Math.min(Math.min(nums[i]*max, nums[i]*min), nums[i]);
        		max=temp;//notice,别还要用就把值覆盖了
        	}
        	maxProduct=Math.max(maxProduct, max);
        }
        return maxProduct;
    }
}

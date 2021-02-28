// Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
//
// Each element in the array represents your maximum jump length at that position.
//
// Your goal is to reach the last index in the minimum number of jumps.
//
// You can assume that you can always reach the last index.
//
//  
// Example 1:
//
//
// Input: nums = [2,3,1,1,4]
// Output: 2
// Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
//
//
// Example 2:
//
//
// Input: nums = [2,3,0,1,4]
// Output: 2
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
    //用贪心
    public int jump(int[] nums) {
    	int left=0;
    	int right=0;
    	int rightest;//通过[left,right]可以达到的最远点
    	int step=0;
    	while(right<nums.length-1){
    		rightest=right;
    		while(left<=right){
    			rightest=Math.max(rightest, left+nums[left]);
    			left++;
    		}
    		if(rightest==right){//不可达
    			return Integer.MAX_VALUE;
    		}
    		step++;
    		right=rightest;
    	}
    	return step;
    }
    //dp超时：
    // public int jump(int[] nums) {
    //     int dp[]=new int[nums.length];//dp[i]:从i位置跳到n-1位置的最小步数
    //     dp[nums.length-1]=0;//已经在了，不需要跳了
    //     for(int i=nums.length-2;i>=0;i--){
    //     	int minStep=Integer.MAX_VALUE;
    //     	for(int j=1;j<=nums[i] && i+j<nums.length;j++){//dp[i]=min(dp[i位置可以跳到的位置])+1;
    //     		minStep=Math.min(minStep, dp[i+j]);
    //     	}
    //     	if(minStep==Integer.MAX_VALUE){//避免溢出
    //     		dp[i]=minStep;
    //     	}else{
    //     		dp[i]=minStep+1;
    //     	}
    //     }
    //     return dp[0];
    // }
}

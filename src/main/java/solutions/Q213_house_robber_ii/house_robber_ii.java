// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
// Given a list of non-negative integers nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
//
//  
// Example 1:
//
//
// Input: nums = [2,3,2]
// Output: 3
// Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
//
//
// Example 2:
//
//
// Input: nums = [1,2,3,1]
// Output: 4
// Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
// Total amount you can rob = 1 + 3 = 4.
//
//
// Example 3:
//
//
// Input: nums = [0]
// Output: 0
//
//
//  
// Constraints:
//
//
// 	1 <= nums.length <= 100
// 	0 <= nums[i] <= 1000
//
//


class Solution {
        //213. House Robber II 所有房子围成一个圈,意味着第一个房子与最后一个房子是相邻的
    //若可偷可不投最后一个房子，则第一个房子肯定不能偷，从第二个房子开始决策;若不偷最后一个房子，则转化为198
    public int rob(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
    	return Math.max(rob(0,nums.length-2,nums),rob(1, nums.length-1, nums));
    }
    //在houses[start,end]中偷取最大价值
    private int rob(int start,int end,int[] houses){
    	if(start>end){
    		return 0;
    	}
    	int rob=houses[start];
    	int notRob=0;
    	for(int i=start+1;i<=end;i++){
    		int curRob=notRob+houses[i];//偷当前房
    		notRob=Math.max(rob, notRob);
    		rob=curRob;
    	}
    	return Math.max(rob, notRob);
    }
}

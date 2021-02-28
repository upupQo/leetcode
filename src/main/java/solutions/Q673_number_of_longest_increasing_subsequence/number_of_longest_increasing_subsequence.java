// Given an integer array nums, return the number of longest increasing subsequences.
//
// Notice that the sequence has to be strictly increasing.
//
//  
// Example 1:
//
//
// Input: nums = [1,3,5,4,7]
// Output: 2
// Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
//
//
// Example 2:
//
//
// Input: nums = [2,2,2,2,2]
// Output: 5
// Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
//
//
//
//  
// Constraints:
//
//
// 	1 <= nums.length <= 2000
// 	-106 <= nums[i] <= 106
//
//


class Solution {
    //673.最长递增子序列的数量。dp[i]以nums[i]为结尾的最长递增子序列的(长度,数量)，要记录两个信息，因此即可以单独定义一个类，也可以用平行数组
    //采用二维平行数组:dp[i][0]:以nums[i]为结尾的最长递增子序列的长度,dp[i][1]:为该长度的数量
    public int findNumberOfLIS(int[] nums) {
        if(nums.length<=1){
        	return nums.length;
        }
        //1.填dp数组
        int[][] dp=new int[nums.length][2];//默认初始化为0
        dp[0][0]=1;
        dp[0][1]=1;
        int lis=1;//记录最大长度,主过程类似300.Original
        for(int i=1;i<nums.length;i++){
        	dp[i][0]=1;//以nums[i]为结尾的最长递增子序列长度至少为1
        	dp[i][1]=1;//数量至少为1
        	for(int k=i-1;k>=0;k--){
        		if(nums[k]<nums[i]){
        			if(dp[k][0]+1>dp[i][0]){//nums[k]<nums[i] 且链接后的长度大于当前长度
	        			dp[i][0]=dp[k][0]+1;
	        			dp[i][1]=dp[k][1];
        			}else if(dp[k][0]+1==dp[i][0]){//nums[k]<nums[i] 且链接后的长度==当前长度
        				dp[i][1]+=dp[k][1];
        			}
        		}
        	}
        	lis=Math.max(lis, dp[i][0]);
        }
        //2.遍历dp,找长度为lis的总数量
        int count=0;
        for(int i=0;i<nums.length;i++){
        	if(dp[i][0]==lis){
        		count+=dp[i][1];
        	}
        }
        return count;
    }
}

// A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.
//
// For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.
//
// Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.
//
// Example 1:
//
//
// Input: [1,7,4,9,2,5]
// Output: 6
// Explanation: The entire sequence is a wiggle sequence.
//
//
// Example 2:
//
//
// Input: [1,17,5,10,13,15,10,5,16,8]
// Output: 7
// Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
//
//
// Example 3:
//
//
// Input: [1,2,3,4,5,6,7,8,9]
// Output: 2
//
// Follow up:
// Can you do it in O(n) time?
//
//
//


class Solution {
    //376.最长摇摆序列长度--动态规划解
    //dp[i][0]:以arr[i]为结尾,最近一段为上升段的序列长度
   //dp[i][1]:以arr[i]为结尾,最近一段为下降段段的序列长度
    public int wiggleMaxLength(int[] nums) {
    	int[][] dp=new int[nums.length][2];
    	int max=0;
        for(int i=0;i<nums.length;i++){
        	dp[i][0]=1;
        	dp[i][1]=1;
        	for(int j=i-1;j>=0;j--){
        		if(nums[j]==nums[i]){//值相等，i位置可以尝试替代j位置
        			dp[i][0]=Math.max(dp[i][0], dp[j][0]);
        			dp[i][1]=Math.max(dp[i][1], dp[j][1]);
        		}else if(nums[j]>nums[i]){//组成下降段
        			dp[i][1]=Math.max(dp[i][1], dp[j][0]+1);
        		}else{//组成上升段
        			dp[i][0]=Math.max(dp[i][0], dp[j][1]+1);
        		}
        	}
        	max=Math.max(max, dp[i][0]);
        	max=Math.max(max, dp[i][1]);
        }
        return max;
    }
}

//
// On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
//
// Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
//
//
// Example 1:
//
// Input: cost = [10, 15, 20]
// Output: 15
// Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
//
//
//
// Example 2:
//
// Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
// Output: 6
// Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
//
//
//
// Note:
//
// cost will have a length in the range [2, 1000].
// Every cost[i] will be an integer in the range [0, 999].
//
//


class Solution {
    //746. Min Cost Climbing Stairs 到楼顶的最小花费.设到i阶楼梯的最下花费dp[i]=cost[i]+min(dp[i-1],dp[i-2]),第一步可以直接到0,1阶楼梯
    public int minCostClimbingStairs(int[] cost) {
        int n=cost.length;//notice设楼顶是第n阶楼梯,花费为0
        if(n==0){
        	return 0;
        }
        if(n==1){
        	return cost[0];
        }
        if(n==2){
        	return Math.min(cost[0], cost[1]);
        }
        int n1=cost[0],n2=cost[1],n3;
        for(int i=2;i<n;i++){
        	n3=cost[i]+Math.min(n1, n2);
        	n1=n2;
        	n2=n3;
        }
        return Math.min(n1, n2);
    }
}

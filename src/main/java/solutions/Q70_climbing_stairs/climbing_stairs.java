// You are climbing a staircase. It takes n steps to reach the top.
//
// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//
//  
// Example 1:
//
//
// Input: n = 2
// Output: 2
// Explanation: There are two ways to climb to the top.
// 1. 1 step + 1 step
// 2. 2 steps
//
//
// Example 2:
//
//
// Input: n = 3
// Output: 3
// Explanation: There are three ways to climb to the top.
// 1. 1 step + 1 step + 1 step
// 2. 1 step + 2 steps
// 3. 2 steps + 1 step
//
//
//  
// Constraints:
//
//
// 	1 <= n <= 45
//
//


class Solution {
    //70.爬楼梯，每次能爬1或2步,到楼顶一共有多少种方式dp[n]=dp[n-1]+dp[n-2]-->压缩dp
    public int climbStairs(int n) {
        if(n<=1){
        	return 1;
        }
        if(n==2){
        	return 2;
        }
        //填dp
        int n1=1,n2=2,n3;
        n-=2;
        while(n>0){
        	n3=n1+n2;
        	n1=n2;
        	n2=n3;
        	n--;
        }
        return n2;
    }
}

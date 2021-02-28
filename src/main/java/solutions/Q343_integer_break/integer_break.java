// Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.
//
// Example 1:
//
//
//
// Input: 2
// Output: 1
// Explanation: 2 = 1 + 1, 1 × 1 = 1.
//
//
// Example 2:
//
//
// Input: 10
// Output: 36
// Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
//
// Note: You may assume that n is not less than 2 and not larger than 58.
//
//


class Solution {
    //dp[i]：数字i切分后可以得到的最大乘积,至少切一次
    //dp[i]=j*max(dp[i-j],(i-j)),j∈[1,i-1];切出一个j后，i-j可以继续切割也可以不再切割
    public int integerBreak(int n) {
        if(n<2){
        	return -1;
        }
        int[] dp=new int[n+1];
        dp[1]=1;
        dp[2]=1;//必须切割
        for(int i=3;i<=n;i++){
        	int maxProduct=0;
        	for(int j=1;j<i;j++){//分割出一个数j
        		maxProduct=Math.max(maxProduct, j*Math.max(dp[i-j],(i-j)));//key.对i-j继续切或者不再切
        	}
        	dp[i]=maxProduct;
        }
        for(int i=0;i<=n;i++){
        	System.out.println(dp[i]);
        }
        return dp[n];
    }
}

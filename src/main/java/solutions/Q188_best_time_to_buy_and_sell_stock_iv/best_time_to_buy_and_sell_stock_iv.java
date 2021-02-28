// You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
//
// Design an algorithm to find the maximum profit. You may complete at most k transactions.
//
// Notice that you may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//
//  
// Example 1:
//
//
// Input: k = 2, prices = [2,4,1]
// Output: 2
// Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
//
//
// Example 2:
//
//
// Input: k = 2, prices = [3,2,6,5,0,3]
// Output: 7
// Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
//
//
//  
// Constraints:
//
//
// 	0 <= k <= 100
// 	0 <= prices.length <= 1000
// 	0 <= prices[i] <= 1000
//
//


class Solution {
    //188.[0,n-1]时间段内最多完成k次交易可以获得的做大利益--k个无重叠子数组最大和
    //dp[i][j]:[0,i]时间段内，最多进行j场交易可以获得的最大利益
    //case1.第i天卖 dp[i][j]=max(dp[k-1][j-1]+prices[i]-prices[k]) 枚举最近买入的那天k,k∈[0,i-1]
    //case2.第i天不卖 dp[i][j]=dp[i-1][j]
    //综上在case1与case2中取大的利益:dp[i][j]=max(dp[i-1][j],dp[k-1][j-1]+prices[i]-prices[k]),k∈[0,i-1],直接这么写为O(n*n*n),继续分析dp依赖表达式
    //                                      =max(dp[i-1][j],prices[i]+max(dp[k-1][j-1]-prices[k])),k∈[0,i-1],对于max(dp[k-1][j-1]-prices[k])，可以在填dp矩阵的过程中顺便记录下来,没必要每次都去算 key.O(n*n)
    //按照122的思路会得到错解，因为对于122，没有次数限制，只要有利益，就都可以获得，但是对于本题，为了获取全局最大利益，局部利益可以舍去eg:2,{1,2,4,2,5,7,2,4,9,0},按122思路只能得12，但应该能得13
    public int maxProfit(int k, int[] prices) {
    	if(k<1){
    		return 0;
    	}
    	if(k>=prices.length/2){//退化为122.只要有利益，就都可以获得O(n)
    		int profit=0;
    		for(int i=1;i<prices.length;i++){
    			if(prices[i]>prices[i-1]){
    				profit+=prices[i]-prices[i-1];
    			}
    		}
    		return profit;
    	}
    	//为了整体利益，可能要舍弃局部利益O(n*n)
    	int m=prices.length;
    	int[][] dp=new int[m][k+1];//dp[i][j]:[0,i]天最多进行j场交易可以获得的最大利益
    	//第一行默认初始化为0(仅[0,0]天，无法完成一次有效交易)，第一列默认初始化为0([0,i]天只能进行0次交易即不允许交易)
    	//采用逐列填，每列从上往下填(考虑到preMax语义)
    	for(int j=1;j<=k;j++){
    		int preMax=0-prices[0];//key,遵循语义
    		for(int i=1;i<m;i++){
    			dp[i][j]=Math.max(dp[i-1][j], prices[i]+preMax);
    			preMax=Math.max(preMax, dp[i-1][j-1]-prices[i]);//key
    		}
    	}
    	return dp[m-1][k];
    }
}

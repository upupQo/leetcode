// You are given an array prices for which the ith element is the price of a given stock on day i.
//
// Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
//
// Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
//
//  
// Example 1:
//
//
// Input: prices = [7,1,5,3,6,4]
// Output: 7
// Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
// Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
//
//
// Example 2:
//
//
// Input: prices = [1,2,3,4,5]
// Output: 4
// Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
// Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
//
//
// Example 3:
//
//
// Input: prices = [7,6,4,3,1]
// Output: 0
// Explanation: In this case, no transaction is done, i.e., max profit = 0.
//
//
//  
// Constraints:
//
//
// 	1 <= prices.length <= 3 * 104
// 	0 <= prices[i] <= 104
//
//


class Solution {
    //比较符合语义的写法
    public int maxProfit(int[] prices) {
    	int profit=0;
    	int buy=0;//假设在当天买，可以持续到什么时候卖。若无法持续，相当于今天不买
    	int sell;
    	while(buy<prices.length){
    		sell=buy+1;
    		while(sell<prices.length && prices[sell]>=prices[sell-1]){
    			sell++;
    		}
    		profit+=prices[sell-1]-prices[buy];
    		buy=sell;
    	}
    	return profit;
    }
    public int maxProfitOri(int[] prices) {
        int profit=0;
        for(int i=1;i<prices.length;i++){
        	if(prices[i]>=prices[i-1]){
        		profit+=prices[i]-prices[i-1];//昨天晚上又涨了几块
        	}
        	//低点买，达到最高点后卖
        }
        return profit;
    }
}

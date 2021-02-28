// You are given an array prices where prices[i] is the price of a given stock on the ith day.
//
// You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
//
// Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
//
//  
// Example 1:
//
//
// Input: prices = [7,1,5,3,6,4]
// Output: 5
// Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
// Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
//
//
// Example 2:
//
//
// Input: prices = [7,6,4,3,1]
// Output: 0
// Explanation: In this case, no transactions are done and the max profit = 0.
//
//
//  
// Constraints:
//
//
// 	1 <= prices.length <= 105
// 	0 <= prices[i] <= 104
//
//


class Solution {
    //121买股票 仅允许最多一次交易
    //对于每个位置i，与[0,i-1]区间的最小值做差即可得‘在今天收盘’获得最大利润。--相当于枚举收盘日
    public int maxProfit(int[] prices) {
        int max=0;
        int min=Integer.MAX_VALUE;//[0,i-1]区间的最小值
        for(int i=0;i<prices.length;i++){
        	max=Math.max(max, prices[i]-min);
        	min=Math.min(min, prices[i]);
        }
        return max;
    }
}

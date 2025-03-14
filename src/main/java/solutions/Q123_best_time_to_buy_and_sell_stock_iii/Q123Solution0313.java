import java.util.Arrays;

public class Q123Solution0313 {
    public int maxProfit(int[] prices) {
        // help1[i]: prices[0,i]只做一次交易，可以获得的最大收益：对于第i天的视角，要么不交易(卖出)，help1[i] = help1[i-1]; 要么交易(卖出)，可以取得的最大收益是prices[i] - min(prices[0,i-1])
        // help2[i]: prices[i,n-1]只做一次交易，可以获得的最大收益：基于买卖规则，对于第i天，要么不交易(买入)，help2[i] = help2[i+1];要么交易(买入)，可以取得的未来最大收益是max(prices[i+1, n-1) - prices[i]

        if (prices.length < 2) {
            return 0;
        }

        // 开始coding
        int[] help1 = new int[prices.length];
        int[] help2 = new int[prices.length];

        help1[0] = 0;
        int min = prices[0];
        for (int i = 1; i <= prices.length -1; i++) {
            help1[i] = Math.max(help1[i - 1], prices[i] - min);
            min = Math.min(prices[i], min);
        }

        help2[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            help2[i] = Math.max(help2[i + 1], max - prices[i]);
            max = Math.max(prices[i], max);
        }


        int result = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            result = Math.max(result, help1[i] + help2[i + 1]);
        }

        result = Math.max(result, help1[prices.length - 1]);

        return result;

    }

    public static void main(String[] args) {
        int[] prices = new int[]{1,2};
        new Q123Solution0313().maxProfit(prices);
    }
}

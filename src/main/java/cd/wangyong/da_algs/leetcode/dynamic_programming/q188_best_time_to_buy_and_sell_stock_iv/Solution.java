package cd.wangyong.da_algs.leetcode.dynamic_programming.q188_best_time_to_buy_and_sell_stock_iv;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 * @author wangyong
 * @since 2020/5/29
 */
public class Solution {

    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int max = 0, maxTmp = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int lastMax = max;
            max = Math.max(lastMax, maxTmp + prices[i]);
            maxTmp = Math.max(maxTmp, lastMax - prices[i]);
        }
        return max;
    }

    public int maxProfit(int k, int[] prices) {
        if (prices.length < 2 || k == 0) return 0;

        if (k > prices.length / 2) return maxProfit(prices);

        int[][][] dp = new int[prices.length][k + 1][2];

        for (int i = 0; i < prices.length; i++) {
            for (int j = k; j >= 1; j--) {
                if (i - 1 == -1) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = - prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[prices.length -1][k][0];
    }
}

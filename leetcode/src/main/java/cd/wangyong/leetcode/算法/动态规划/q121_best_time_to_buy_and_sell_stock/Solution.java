package cd.wangyong.leetcode.算法.动态规划.q121_best_time_to_buy_and_sell_stock;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/bao-li-mei-ju-dong-tai-gui-hua-chai-fen-si-xiang-b/
 * @author wangyong
 * @since 2020/5/28
 */
public class Solution {
    /**
     * 暴力破解，复杂度o(n2)
     */
    public int maxProfit1(int[] prices) {
        int max = 0, maxTmp = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > 0) {
                    maxTmp = Math.max(maxTmp, prices[j] - prices[i]);
                }
            }
            max = Math.max(max, maxTmp);
        }
        return max;
    }

    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) min = prices[i];
            else if (prices[i] - min > maxprofit) maxprofit = prices[i] - min;
        }
        return maxprofit;
    }

    /**
     * 动态规划，关键是状态机思考
     * 状态转移方程
     */
    public int maxProfit3(int[] prices) {
        if (prices.length < 2) return 0;

        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    public int maxProfit4(int[] prices) {
        if (prices.length < 2) return 0;

        // int[][] dp = new int[prices.length][2];
        // dp[0][0] = 0;
        // dp[0][1] = -prices[0];

        int max = 0, maxTmp = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            // dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            max = Math.max(max, maxTmp + prices[i]);
            // dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            maxTmp = Math.max(maxTmp, -prices[i]);
        }
        // return dp[prices.length - 1][0];
        return max;
    }







}

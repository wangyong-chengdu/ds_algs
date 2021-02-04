package cd.wangyong.leetcode.算法.动态规划;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 买卖股票的最佳时机 {

    public int maxProfit(int[] prices) {
        // 处理边界
        if (prices.length < 2) return 0;

        // dp[i][j]表示第i天的最大利润，j = 0,不持有股票，j = 1表示持有股票
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], - prices[i]);
        }
        return dp[prices.length - 1][0];
    }
}

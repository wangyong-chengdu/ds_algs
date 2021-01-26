package cd.wangyong.leetcode.算法.动态规划.knapsack;

/**
 * @author wangyong
 * @since 2020/6/5
 */
public class Solution {

    public int knapsack(int w, int n, int[] wt, int[] val) {
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (w - wt[i - 1] < 0) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wt[i - 1]] + val[i - 1]);
            }
        }
        return dp[n][w];
    }
}

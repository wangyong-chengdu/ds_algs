package cd.wangyong.leetcode.算法.动态规划;

/**
 * @author andy
 * @since 2021/2/19
 */
public class 零钱兑换 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = initDp(amount);

        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);// dp[i - coin] + 1
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    private int[] initDp(int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            dp[i] = amount + 1; // 设置天花板
        }
        return dp;
    }
}

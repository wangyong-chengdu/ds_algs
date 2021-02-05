package cd.wangyong.leetcode.算法.动态规划;

/**
 * @author andy
 * @since 2021/2/5
 */
public class 打家劫舍 {

    /**
     * - 思路：dp[i][j], i表示第几间房屋，j为0或1,0表示没偷、1表示偷了， dp表示到第几间的最高金额
     - 初始化：dp[0][0] =0, dp[0][1] = nums[0]
     - 递推关系：
     - dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
     - dp[i][1] = dp[i - 1][0] + nums[i];
     - 时间复杂度：o(n)，空间复杂度：o(n)
     */
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }
}

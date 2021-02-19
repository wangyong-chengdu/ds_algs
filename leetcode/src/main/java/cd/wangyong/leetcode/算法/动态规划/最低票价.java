package cd.wangyong.leetcode.算法.动态规划;


/**
 * @author andy
 * @since 2021/2/19
 */
public class 最低票价 {

    public int mincostTickets(int[] days, int[] costs) {
        int len = days.length, end = days[len - 1], begin = days[0];
        int[] dp = new int[end + 31]; // 多扩几天，省得判断 365 的限制

        for (int day = end, i = len - 1; day >= begin; day--) {
            if (day == days[i]) {
                dp[day] = Math.min(dp[day + 1] + costs[0], dp[day + 7] + costs[1]);
                dp[day] = Math.min(dp[day], dp[day + 30] + costs[2]);
                i--; // 别忘了递减一天
            }
            else {
                dp[day] = dp[day + 1]; // 不需要出门
            }
        }
        return dp[begin]; // 从后向前遍历，返回最前的 minDay
    }
}

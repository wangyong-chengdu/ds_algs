package cd.wangyong.leetcode.算法.动态规划;

import java.util.Arrays;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 俄罗斯套娃信封问题 {

    public int maxEnvelopes(int[][] envelopes) {
        // 当宽度相等时，比如[[1,5],[1,6]]，排序程[1,6][1,5]防止变成 2个
        Arrays.sort(envelopes, (a, b) -> a[0] - b[0] != 0 ? a[0] - b[0] : b[1] - a[1]);

        // extract the second dimension and run LIS
        int[] secondDim = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; ++i) secondDim[i] = envelopes[i][1];

        return lengthOfLIS(secondDim);
    }

    public int lengthOfLIS(int[] nums) {
        // dp[i]表示长度为i的最长上升子序列的最小值，那么dp[i]一定是一个递增序列
        int[] dp = new int[nums.length];
        int len = 0;

        // 遍历数组
        for (int num : nums) {
            // 二分查找num是否在dp数组中存在，存在，则返回索引号，否则返回待插入点
            int i = Arrays.binarySearch(dp, 0, len, num);

            // 可以保证dp[i]存放的事最小值
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;

            // i是数组下标，存在，且i == len, 说明长度可以 + 1了。
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}

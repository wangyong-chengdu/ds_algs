package cd.wangyong.leetcode.算法.动态规划.q53.maximum_subarray;

/**
 * @author wangyong
 * @since 2020/5/28
 */
public class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int maxSumTmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxSumTmp = Math.max(maxSumTmp + nums[i], nums[i]);
            maxSum = Math.max(maxSum, maxSumTmp);
        }
        return maxSum;
    }
}

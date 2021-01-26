package cd.wangyong.leetcode.算法.递归;

import java.util.Arrays;

/**
 * @author wangyong
 * @since 2020/6/5
 */
public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        int subSum = sum / 2;
        return isExist(nums, 0, subSum);
    }

    private boolean isExist(int[] nums, int i, int sum) {
        if (i == nums.length) return false;
        if (sum <= 0) return false;
        if (nums[i] == sum) return true;
        return isExist(nums, i + 1, sum - nums[i]) || isExist(nums, i + 1, sum);
    }

}

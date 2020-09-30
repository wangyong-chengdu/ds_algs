package cd.wangyong.da_algs.leetcode.two_pointer.q_3sum_closest;

import java.util.Arrays;

/**
 * @author wangyong
 * @since
 */
public class Solution {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        // nums is too small
        int result = nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1];
        if (result < target) {
            return result;
        }

        int maxI = nums.length - 3;
        for (int i = 0; i <= maxI; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) {
                    return sum;
                }
                else if (sum > target) {
                    r--;
                }
                else {
                    l++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 2, 1, -3};
        Solution solution = new Solution();
        System.out.println(solution.threeSumClosest(nums, 1));
    }
}

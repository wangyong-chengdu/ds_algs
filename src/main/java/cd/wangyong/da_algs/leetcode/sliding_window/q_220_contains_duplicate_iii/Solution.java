package cd.wangyong.da_algs.leetcode.sliding_window.q_220_contains_duplicate_iii;

/**
 * @author wangyong
 * @since 2020/6/30
 */
public class Solution {

    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length - 1; i++)
            for (int j = i + 1; j < nums.length && j <= i + k; j++)
                if (Math.abs((long)nums[i] - (long)nums[j]) <= t) return true;
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,2147483647};
        System.out.println(containsNearbyAlmostDuplicate(nums, 1, 2147483647));
    }
}

package cd.wangyong.da_algs.leetcode.two_pointer.q_remove_duplicates_from_sorted_array;

/**
 * @author wangyong
 * @since 2020/05/23
 */
public class Solution {

    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return 1;
        }

        int next = 0;
        for (int i = 0; i < nums.length; i++) {
            while (i > 0 && i < nums.length && nums[i] == nums[i - 1]) {
                i++;
            }

            if (i < nums.length) {
                nums[next] = nums[i];
                next++;
            }
        }
        return next;
    }

    public int removeDuplicates(int[] nums) {
        int next = nums.length > 0 ? 1 : 0; // next is point to accepted element index + 1;
        for (int ele : nums) {
            if (ele > nums[next - 1]) {
                nums[next++] = ele;
            }
        }
        return next;
    }
}

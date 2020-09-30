package cd.wangyong.da_algs.leetcode.binary_search.q_search_insert_position;

/**
 * @author wangyong
 * @since 2020/5/25
 */
public class Solution {

    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int mid = low + ((high - low) >>> 1);
        while (low <= high) {
            if (nums[mid] > target) high = mid - 1;
            else if (nums[mid] < target) low = mid + 1;
            else {
                high = mid - 1;
                if (high < 0 || nums[high] < target) return mid;
            }
            mid = low + ((high - low) >>> 1);
        }
        return low;
    }
}

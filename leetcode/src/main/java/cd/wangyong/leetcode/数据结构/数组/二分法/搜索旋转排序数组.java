package cd.wangyong.leetcode.数据结构.数组.二分法;

/**
 * @author andy
 * @since 2021/2/5
 */
public class 搜索旋转排序数组 {

    public int search(int[] nums, int target) {
        return searchHelper(nums, 0, nums.length - 1, target);
    }

    private int searchHelper(int[] nums, int left, int right, int target) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;

        // 用于加速，万一瞎猫碰到死耗子呢
        if (nums[left] == target) return left;
        if (nums[mid] == target) return mid;
        if (nums[right] == target) return right;

        // mid左边区间有序，则判断目标值是否在区间内；反之，右半边有序
        if (nums[left] < nums[mid]) {
            if (target > nums[left] && target < nums[mid])
                return searchHelper(nums, left + 1, mid - 1, target);
            else
                return searchHelper(nums, mid + 1, right - 1, target);
        }
        // 右边有序
        else {
            if (target > nums[mid] && target < nums[right]) return searchHelper(nums, mid + 1, right - 1, target);
            else return searchHelper(nums, left + 1, mid - 1, target);
        }
    }
}

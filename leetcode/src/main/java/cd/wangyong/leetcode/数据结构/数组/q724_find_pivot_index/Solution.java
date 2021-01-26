package cd.wangyong.leetcode.数据结构.数组.q724_find_pivot_index;

/**
 * @author wangyong
 * @since 2020/7/1
 */
public class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return 0;

        int sum = 0;
        for (int i = 0; i < n; i++) sum += nums[i];

        int lSum = 0, rSum = sum;
        for (int i = 0; i < n; i++) {
            if (i - 1 >= 0) lSum += nums[i - 1];
            rSum -= nums[i];
            if (lSum == rSum) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.pivotIndex(new int[]{1,7,3,6,5,6}));
        System.out.println(solution.pivotIndex(new int[]{-1,-1,-1,-1,-1,0}));
    }

}

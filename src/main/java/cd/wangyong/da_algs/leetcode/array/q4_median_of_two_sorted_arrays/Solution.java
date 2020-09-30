package cd.wangyong.da_algs.leetcode.array.q4_median_of_two_sorted_arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyong
 * @since 2020/6/4
 */
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        List<Integer> list = merge(nums1, nums2, len);
        double result = len % 2 == 0 ? ((double)list.get(len / 2 - 1) + (double) list.get(len / 2)) / 2 : ((double)list.get(len / 2)) / 2;
        return result;
    }

    private List<Integer> merge(int[] nums1, int[] nums2, int len) {
        List<Integer> list = new ArrayList<>(len);
        int i = 0, j = 0;
        while (list.size() < len) {
            if (i == nums1.length) list.add(nums2[j++]);
            else if (j == nums2.length) list.add(nums1[i++]);
            else list.add(nums1[i] <= nums2[j] ? nums1[i++] : nums2[j++]);
        }
        return list;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }
}

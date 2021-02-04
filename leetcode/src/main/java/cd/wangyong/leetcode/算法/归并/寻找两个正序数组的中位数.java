package cd.wangyong.leetcode.算法.归并;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 寻找两个正序数组的中位数 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        List<Integer> list = merge(nums1, nums2, len);
        double result = len % 2 == 0 ? ((double)list.get(len / 2 - 1) + (double) list.get(len / 2)) / 2 : (double)list.get(len / 2);
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
}

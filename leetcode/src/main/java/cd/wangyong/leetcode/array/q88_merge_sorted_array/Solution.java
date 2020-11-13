package cd.wangyong.leetcode.array.q88_merge_sorted_array;

/**
 * @author wangyong
 * @since 2020/6/4
 */
public class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        int len = m + n;
        int[] nums3 = new int[len];
        for (int k = 0; k < len; k++) {
            if (i == m) nums3[k] = nums2[j++];
            else if (j == n) nums3[k] = nums1[i++];
            else nums3[k] = (nums1[i] <= nums2[j] ? nums1[i++] : nums2[j++]);
        }
        for (int k = 0; k < len; k++) nums1[k] = nums3[k];
    }
}

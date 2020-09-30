package cd.wangyong.da_algs.leetcode.sliding_window.q129_contains_duplicate_ii;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wangyong
 * @since 2020/6/30
 */
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>(); // set最大size为k
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) set.remove(nums[i - k]);
        }
        return false;
    }
}

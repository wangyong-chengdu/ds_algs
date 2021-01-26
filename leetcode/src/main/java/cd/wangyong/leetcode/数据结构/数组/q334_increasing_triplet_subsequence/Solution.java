package cd.wangyong.leetcode.数据结构.数组.q334_increasing_triplet_subsequence;

public class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int right = 1, left = 0;
        while (right < nums.length && left <= nums.length -3) {
            if (nums[right] <= nums[right - 1]) {
                left = right;
                right++;
                continue;
            }
            if (right - left == 2) return true;
            right++;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.increasingTriplet(new int[]{5,1,5,5,2,5,4});
    }
}

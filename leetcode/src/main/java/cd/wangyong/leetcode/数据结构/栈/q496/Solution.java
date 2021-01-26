package cd.wangyong.leetcode.数据结构.栈.q496;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/next-greater-element-i/
 */
public class Solution {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        if (nums1.length > 1000 || nums2.length > 1000) {
            throw new IllegalArgumentException();
        }

        int capacity = (int) (nums1.length / 0.75f);
        Map<Integer, Integer> map = new HashMap<>(capacity);


        Stack<Integer> stack = new Stack<>();
        for (int ele : nums2) {
            // ֻҪջ��peek��eleС��˵��ele������Ԫ�ش�
            while (!stack.isEmpty() && stack.peek() < ele) {
                map.put(stack.pop(), ele);
            }
            stack.push(ele);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]) != null ? map.get(nums1[i]) : -1;
        }
        return result;
    }

    public static void main(String[] args) {

        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};

        Solution solution = new Solution();
        int[] element = solution.nextGreaterElement(nums1, nums2);
    }
}

package cd.wangyong.leetcode.stack.q31;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
 *
 * @author wangyong
 * @since 2020/4/25
 */
public class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || pushed.length == 0 || popped == null || popped.length == 0) {
            return true;
        }

        if (pushed.length != popped.length) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();

        int j = 0;
        for (int ele : pushed) {
            stack.push(ele);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    public static void testCase1(String[] args) {
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 5, 3, 2, 1};

        Solution solution = new Solution();
        System.out.println(solution.validateStackSequences(pushed, popped));
    }

    public static void main(String[] args) {
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 3, 5, 1, 2};

        Solution solution = new Solution();
        System.out.println(solution.validateStackSequences(pushed, popped));
    }
}

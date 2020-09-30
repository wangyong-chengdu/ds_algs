package cd.wangyong.da_algs.leetcode.string.q38_count_and_say;

import java.util.Stack;

/**
 * @author wangyong
 * @since 2020/7/3
 */
public class Solution {
    public String countAndSay(int n) {
        int num = 1;
        if (n == 1) return "1";
        while (--n > 0)
            num = computeNextNum(num);
        return String.valueOf(num);
    }

    private int computeNextNum(int num) {
        int count = 0, digit = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        while (num != 0) {
            int tempDigit = num % 10;
            if (tempDigit == digit) {
                count++;
            }
            else {
                if (count != 0) {
                    stack.push(digit);
                    stack.push(count);
                }
                digit = tempDigit;
                count = 1;
            }
            num /= 10;
        }
        stack.push(digit);
        stack.push(count);

        int sum = 0;
        while (!stack.isEmpty())
            sum = 10 * sum + stack.pop();
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countAndSay(2));
    }

}

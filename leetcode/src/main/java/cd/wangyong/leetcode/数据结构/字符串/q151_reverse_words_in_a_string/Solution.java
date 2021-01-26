package cd.wangyong.leetcode.数据结构.字符串.q151_reverse_words_in_a_string;

import java.util.Stack;

/**
 * @author wangyong
 * @since 2020/7/1
 */
public class Solution {
    public String reverseWords(String s) {
        if (s.length() == 0) return s;
        Stack<String> stack = new Stack<>();
        int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                if (left != Integer.MIN_VALUE && right != Integer.MIN_VALUE) {
                    stack.push(s.substring(left, right + 1));
                }
                left = Integer.MIN_VALUE;
                right = Integer.MIN_VALUE;
                continue;
            }
            if (left == Integer.MIN_VALUE) left = i;
            right = i;
        }
        if (left != Integer.MIN_VALUE && right != Integer.MIN_VALUE) {
            stack.push(s.substring(left, right + 1));
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop()).append(" ");
        return sb.toString().substring(0, sb.length());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.reverseWords("the sky is blue"));
        System.out.println(solution.reverseWords("the"));
    }
}

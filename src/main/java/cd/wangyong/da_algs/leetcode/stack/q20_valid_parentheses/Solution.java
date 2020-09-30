package cd.wangyong.da_algs.leetcode.stack.q20_valid_parentheses;

import java.util.Stack;

/**
 * @author wangyong
 * @since 2020/5/27
 */
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push( ']');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }

}

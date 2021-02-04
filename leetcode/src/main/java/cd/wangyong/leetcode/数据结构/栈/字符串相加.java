package cd.wangyong.leetcode.数据结构.栈;

import java.util.Stack;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 字符串相加 {

    public String addStrings(String num1, String num2) {
        Stack<Character> st1 = new Stack<>();
        Stack<Character> st2 = new Stack<>();

        for (int i = 0; i < num1.length(); i++) {
            st1.push(num1.charAt(i));
        }

        for (int i = 0; i < num2.length(); i++) {
            st2.push(num2.charAt(i));
        }

        Stack<Character> st3 = new Stack<>();
        int temp = 0;
        while (!st1.empty() && !st2.empty()) {
            int a = Character.getNumericValue(st1.pop());
            int b = Character.getNumericValue(st2.pop());
            int sum = a + b + temp;
            temp = sum / 10;
            st3.push((char)(sum % 10 + '0'));
        }

        Stack<Character> st4 = st1.empty() ? st2 : st1;
        while (!st4.empty()) {
            if (temp != 0) {
                int a = Character.getNumericValue(st4.pop());
                int sum = a + temp;
                temp = sum / 10;
                st3.push((char)(sum % 10 + '0'));
            }
            else {
                st3.push(st4.pop());
            }
        }
        if (temp != 0) {
            st3.push((char)(temp + '0'));
        }

        StringBuilder sb = new StringBuilder();
        while (!st3.empty()) {
            sb.append(st3.pop());
        }
        return sb.toString();
    }
}

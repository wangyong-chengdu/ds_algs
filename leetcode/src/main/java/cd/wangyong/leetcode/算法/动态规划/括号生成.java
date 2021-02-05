package cd.wangyong.leetcode.算法.动态规划;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author andy
 * @since 2021/2/5
 */
public class 括号生成 {

    public List<String> generateParenthesis(int n) {
        if (n == 0) return Collections.emptyList();

        List<String> result = new LinkedList<>();
        LinkedList<Character> trace = new LinkedList<>();
        List<Character> choices = Arrays.asList('(', ')');
        backtrace(trace, choices, n, n, result);
        return result;
    }

    /**
     * 1.左括号数 = 右括号数；2.从左往右，左括号数 >= 右括号数
     */
    private void backtrace(LinkedList<Character> trace, List<Character> choices, int remainLeftNum, int remainRightNum, List<String> result) {
        if (remainLeftNum > remainRightNum) return; // 剪枝：左边剩下的竟然比右边多，这种肯定不行
        if (remainLeftNum < 0) return; // 左边剩下的或右边剩下的小于0,这种也不行

        // 当左括号和右括号都为0时，解之一
        if (remainLeftNum == 0 && remainRightNum == 0) {
            result.add(trace.stream().map(String::valueOf).collect(Collectors.joining()));
            return;
        }

        for (Character c : choices) {
            trace.add(c);
            if (c == '(') backtrace(trace, choices, remainLeftNum - 1, remainRightNum, result);
            else backtrace(trace, choices, remainLeftNum, remainRightNum - 1, result);
            trace.removeLast();
        }
    }
}

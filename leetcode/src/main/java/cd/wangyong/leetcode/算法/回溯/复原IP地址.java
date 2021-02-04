package cd.wangyong.leetcode.算法.回溯;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 复杂问题，一步一步分解
 * @author andy
 * @since 2021/2/4
 */
public class 复原IP地址 {

    public List<String> restoreIpAddresses(String s) {
        // 处理边界
        if (s == null || s.length() < 4 || s.length() > 12) {
            return Collections.emptyList();
        }

        List<String> res = new ArrayList<>();
        LinkedList<String> trace = new LinkedList<>();
        backtrace(trace, s, 0, 0, res);
        return res;
    }

    private void backtrace(LinkedList<String> trace, String s, int splitTimes, int startIndex, List<String> result) {
        // 回溯法结束条件
        if (startIndex == s.length()) {
            // 正好切分成4份，即xxx.xxx.xxx.xxx，则trace
            if (splitTimes == 4) {
                result.add(String.join(".", trace));
            }
            return;
        }

        // 筛选不满足条件的，提前结束（剪枝）
        int remains = s.length() - startIndex;
        // 不符合ipv4的格式，超过ip地址了(xxx.xxx.xxx.xxx)，则不用继续了。
        if (remains > 3 * (4 - splitTimes)) {
            return;
        }
        // 位数不足，连x.x.x.x都不满足，同样也不用继续了
        if (remains < 1 * (4 - splitTimes)) {
            return;
        }

        // ip地址最多由1 ~ 3位组成
        for (int i = 0; i < 3; i++) {
            if (startIndex + i >= s.length()) break;
            int ipSegment = checkAndGetIpSegment(s, startIndex, startIndex + i);
            if (ipSegment == -1) continue;

            trace.add(ipSegment + "");
            backtrace(trace, s, splitTimes + 1, startIndex + i + 1, result);
            trace.removeLast();
        }
    }

    private int checkAndGetIpSegment(String s, int left, int right) {
        // 大于1位时不能以0开头
        if (right - left + 1 > 1 && s.charAt(left) == '0') return -1;

        int res = 0;
        while (left <= right) {
            res = 10 * res + s.charAt(left++) - '0';
        }

        // ip地址不能大于255
        if (res > 255) return -1;
        else return res;
    }
}

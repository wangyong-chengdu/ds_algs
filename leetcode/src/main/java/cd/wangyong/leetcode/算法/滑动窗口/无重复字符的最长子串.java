package cd.wangyong.leetcode.算法.滑动窗口;

import java.util.HashSet;
import java.util.Set;

/**
 * @author andy
 * @since 2021/2/19
 */
public class 无重复字符的最长子串 {

    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();// 用于判断是否有重复字符
        // 右指针扩展
        while (right < s.length()) {
            while (right < s.length() && !set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                int curLen = right - left + 1;
                maxLen = Math.max(curLen, maxLen);
                right++;
            }

            if (right == s.length()) break;

            // 滑动窗口：左指针滑动
            while (s.charAt(left) != s.charAt(right)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.remove(s.charAt(left));
            left++;
        }
        return maxLen;
    }
}

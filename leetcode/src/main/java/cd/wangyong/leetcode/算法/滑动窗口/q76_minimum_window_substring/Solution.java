package cd.wangyong.leetcode.算法.滑动窗口.q76_minimum_window_substring;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyong
 * @since 2020/5/27
 */
public class Solution {

    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> tCharCountMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tCharCountMap.put(c, tCharCountMap.getOrDefault(c, 0) + 1);
        }
        int tCount = t.length();

        // initial model.
        int minLeft = 0, minRight = 0, minLen = s.length();
        boolean isFound = false;

        // unfixed sliding window，which is special usage of two pointers.
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (!tCharCountMap.containsKey(c)) {
                right++;
                continue;
            }

            int tmpCount = tCharCountMap.get(c) - 1;
            tCharCountMap.put(c, tmpCount);
            if (tmpCount >= 0) tCount--;

            // when tCount = 0, it means t all matched, then check whether current window size is the minimum, shrink left pointer to get the minimum window.
            while (tCount == 0 && left <= right) {
                isFound = true;
                int curWindowSize = right - left + 1;
                if (curWindowSize <= minLen) {
                    minLeft = left;
                    minRight = right;
                    minLen = curWindowSize;
                }

                // shrink left pointer to get the minimum
                char leftC = s.charAt(left);
                if (!tCharCountMap.containsKey(leftC)) {
                    left++;
                    continue;
                }

                tmpCount = tCharCountMap.get(leftC) + 1;
                tCharCountMap.put(leftC, tmpCount);
                if (tmpCount > 0) tCount++;
                left++;
            }
            right++;
        }
        return isFound ? s.substring(minLeft, minRight + 1) : "";
    }
}

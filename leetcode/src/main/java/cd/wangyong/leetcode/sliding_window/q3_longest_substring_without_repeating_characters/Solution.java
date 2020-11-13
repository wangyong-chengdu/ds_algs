package cd.wangyong.leetcode.sliding_window.q3_longest_substring_without_repeating_characters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/*
 * @author wangyong
 * @since 2020/5/12
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            while (right < s.length() && !set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                int curLen = right - left + 1;
                maxLen = curLen >= maxLen ? curLen : maxLen;
                right++;
            }

            if (right == s.length()) break;

            // shrink left point to skip the repeating characters
            while (s.charAt(left) != s.charAt(right)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.remove(s.charAt(left));
            left++;
        }
        return maxLen;
    }


    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        int max = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
                max = Math.max(max, i - left + 1);
            }
            else {
                int newLeft = map.get(s.charAt(i)) + 1;
                for (int j = left; j < newLeft; j++) {
                    map.remove(s.charAt(j));
                }
                map.put(s.charAt(i), i);
                left = newLeft;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));//3
        System.out.println(solution.lengthOfLongestSubstring("pwwkepw"));//4
        System.out.println(solution.lengthOfLongestSubstring("tmmzuxt")); // 5
        System.out.println(solution.lengthOfLongestSubstring("asjrgapa")); //6
    }


}

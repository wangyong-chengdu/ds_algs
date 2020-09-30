package cd.wangyong.da_algs.leetcode.backtrack.q17_letter_combinations_of_a_phone_number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangyong
 * @since 2020/6/4
 */
public class Solution {

    private static Map<Character, List<Character>> map = new HashMap<>();
    static {
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) return Collections.emptyList();
        List<Character> nums = convert(digits.toCharArray());
        int len = nums.size();
        List<Integer> choises = initChoises(len);
        LinkedList<Character> track = new LinkedList<>();
        List<String> result = new LinkedList<>();
        backtrack(track, choises, nums, 0, len, result);
        return result;
    }

    public List<Character> convert(char[] chars) {
        List<Character> list = new ArrayList<>();
        for (char c : chars) list.add(c);
        return list;
    }

    private void backtrack(LinkedList<Character> track, List<Integer> choises, List<Character> nums, int index, int len, List<String> result) {
        if (index == len) {
            result.add(track.stream().map(String::valueOf).collect(Collectors.joining()));
            return;
        }
        for (int i = index; i < len; i++) {
            if (choises.contains(i)) continue;
            choises.remove(Integer.valueOf(i));
            List<Character> characters = map.get(nums.get(i));
            for (Character c : characters) {
                track.add(c);
                backtrack(track, choises, nums, i + 1, len, result);
                track.removeLast();
            }
            choises.add(i);
        }
    }

    private List<Integer> initChoises(int n) {
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) list.add(i);
        return list;
    }
}

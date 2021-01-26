package cd.wangyong.leetcode.数据结构.字典;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wangyong
 * @since 2020/6/29
 */
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            String anagramKey = getAnagramKey(map.keySet(), s);
            if (anagramKey == null) map.computeIfAbsent(s, k -> new ArrayList<>()).add(s);
            else map.get(anagramKey).add(s);
        }
        return new ArrayList<>(map.values());
    }

    private String getAnagramKey(Set<String> keySet, String target) {
        for (String key : keySet)
            if (isAnagram(key, target)) return key;
        return null;
    }

    private boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) return false;
            int count = map.getOrDefault(c, 0) - 1;
            if (count < 0) return false;
            else if (count == 0) map.remove(c);
            else map.put(c, count);
        }
        return map.isEmpty();
    }

}

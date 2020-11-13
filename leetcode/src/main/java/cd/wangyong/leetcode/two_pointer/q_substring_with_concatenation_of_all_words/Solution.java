package cd.wangyong.leetcode.two_pointer.q_substring_with_concatenation_of_all_words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangyong
 * @since 2020/05/23
 */
public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || s.length() == 0 || words.length == 0) {
            return Collections.emptyList();
        }

        // int initCapacity = (int)(words.length / 0.75F + 1F);
        Map<String, Integer> wordCountMap = new HashMap<>();
        Arrays.stream(words).forEach(word -> wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1));

        List<Integer> indexes = new ArrayList<>();
        int len = words[0].length();
        int maxI = s.length() - len * words.length;

        for (int i = 0; i <= maxI; i++) {
            Map<String, Integer> tmpMap = new HashMap<>();
            boolean isNormalExitSubLoop = true;
            for (int j = 0; j < words.length; j++) {
                String str = s.substring(i + j * len, i + j * len + len);
                if (wordCountMap.containsKey(str)) {
                    tmpMap.put(str, tmpMap.getOrDefault(str, 0) + 1);
                    if (tmpMap.get(str) > wordCountMap.get(str)) {
                        isNormalExitSubLoop = false;
                        break;
                    }
                }
                else {
                    isNormalExitSubLoop = false;
                    break;
                }
            }
            if (isNormalExitSubLoop) {
                indexes.add(i);
            }
        }
        return indexes;
    }


    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        if (s == null || words == null || words.length == 0) {
            return res;
        }

        int len = words[0].length(); // length of each word
        Map<String, Integer> map = new HashMap<String, Integer>(); // map for L
        for (String w : words) {
            map.put(w, map.containsKey(w) ? map.get(w) + 1 : 1);
        }

        for (int i = 0; i <= s.length() - len * words.length; i++) {
            Map<String, Integer> copy = new HashMap<String, Integer>(map);
            for (int j = 0; j < words.length; j++) { // checkc if match
                String str = s.substring(i + j * len, i + j * len + len); // next word
                if (copy.containsKey(str)) { // is in remaining words
                    int count = copy.get(str);
                    if (count == 1) {
                        copy.remove(str);
                    }
                    else {
                        copy.put(str, count - 1);
                    }
                    if (copy.isEmpty()) { // matches
                        res.add(i);
                        break;
                    }
                }
                else {
                    break; // not in L
                }
            }
        }
        return res;
    }


}

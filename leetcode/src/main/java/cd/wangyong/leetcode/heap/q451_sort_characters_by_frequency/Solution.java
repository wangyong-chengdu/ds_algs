package cd.wangyong.leetcode.heap.q451_sort_characters_by_frequency;

import java.util.PriorityQueue;

/**
 * @author wangyong
 * @since 2020/6/29
 */
public class Solution {
    // 键索引计数 + 堆排序
    public String frequencySort(String s) {
        // 字母表，ASCII共128个编码
        int[] letters = new int[128];
        for (char c : s.toCharArray())
            letters[c]++;

        PriorityQueue<Character> pq = new PriorityQueue(128, (a, b) -> Integer.compare(letters[(char)b], letters[(char)a]));
        for (int i = 0; i < letters.length; ++i)
            if (letters[i] != 0) pq.offer((char)i);

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            char c = pq.poll();
            while (letters[c]-- > 0)
                sb.append(c);
        }
        return sb.toString();
    }
}

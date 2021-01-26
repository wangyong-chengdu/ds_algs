package cd.wangyong.leetcode.数据结构.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author andy
 * @since 2020/11/18
 */
public class 最长连续序列 {

    /**
     * 以nums[i]作为起点不断扩大查找范围
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        int maxLen = 1;
        int mark = -1;
        Map<Integer, Integer> map = Arrays.stream(nums).boxed().collect(Collectors.toMap(Function.identity(), num -> 1, (k1, k2) -> k1));
        List<Integer> numList = new ArrayList<>(map.keySet());
        for (int num : numList) {
            // 已经标记过，则无需处理
            if (map.get(num) == mark) {
                continue;
            }
            // 连续统计以num为开始的序列
            int nextNum = num + 1;
            while (map.containsKey(nextNum)) {
                map.put(num, map.get(num) + 1);
                map.put(nextNum, mark); // nextNum开始的片段肯定比num开始的片段小
                nextNum++;
            }
            maxLen = Math.max(maxLen, map.get(num));
        };
        return maxLen;
    }

    public static void main(String[] args) {

    }

}

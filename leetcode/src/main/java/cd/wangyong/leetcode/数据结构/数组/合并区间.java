package cd.wangyong.leetcode.数据结构.数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 合并区间 {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][0];

        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // 外围循环每次搞定一个合并后int[]
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            while (i < intervals.length - 1 && intervals[i + 1][0] <= right) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            res.add(new int[]{left, right});
            i++;
        }
        return res.toArray(new int[0][]);
    }
}

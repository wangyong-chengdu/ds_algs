package cd.wangyong.leetcode.数据结构.字典;

import java.util.HashMap;
import java.util.Map;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 数组中重复的数字 {

    public int findRepeatNumber(int[] nums) {
        if (nums.length == 0) return -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int cnt = map.getOrDefault(num, 0);
            if (cnt == 1) return num;
            map.put(num, cnt + 1);
        }
        return -1;
    }
}

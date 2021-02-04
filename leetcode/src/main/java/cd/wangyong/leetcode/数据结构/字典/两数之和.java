package cd.wangyong.leetcode.数据结构.字典;

import java.util.HashMap;
import java.util.Map;

/**
 * @author andy
 * @since 2021/2/3
 */
public class 两数之和 {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int result = target - nums[i];
            if (valueIndexMap.containsKey(result)) {
                return new int[] {valueIndexMap.get(result), i};
            }
            else {
                valueIndexMap.put(nums[i], i);
            }
        }
        return new int[0];
    }
}

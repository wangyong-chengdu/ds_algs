package cd.wangyong.da_algs.leetcode.two_pointer.q1_2sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/
 * <p>
 *
 * @author wangyong
 * @since 2020/5/12
 */
public class Solution {

    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        Map<Integer, List<Integer>> valueIdMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            valueIdMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        Arrays.sort(nums);

        if (nums[0] + nums[1] > target) {
            return new int[0];
        }

        int length = nums.length;
        if (nums[length - 1] + nums[length - 2] < target) {
            return new int[0];
        }

        int i = 0;
        int j = length - 1;
        while (i <= length - 2) {
            while (i < j) {
                if (nums[i] + nums[j] == target) {
                    List<Integer> ids = valueIdMap.get(nums[j]);
                    return new int[]{valueIdMap.get(nums[i]).get(0), ids.get(ids.size() - 1)};
                }
                else if (nums[i] + nums[j] > target) {
                    j--;
                }
                else {
                    break;
                }
            }
            i++;
        }

        return new int[0];
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        Map<Integer, Integer> valueIdMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (valueIdMap.get(temp) != null) {
                return new int[]{valueIdMap.get(temp), i};
            }
            else {
                valueIdMap.put(nums[i], i);
            }
        }
        return new int[0];
    }

    public static void case1(String[] args) {
        int[] num = new int[]{2, 7, 11, 15};
        int target = 9;
        Solution solution = new Solution();
        int[] rst = solution.twoSum(num, target);
    }

    public static void case2(String[] args) {
        int[] num = new int[]{3, 2, 4};
        int target = 6;
        Solution solution = new Solution();
        int[] rst = solution.twoSum(num, target);
        System.out.println(rst);
    }

    public static void main(String[] args) {
        int[] num = new int[]{3, 3};
        int target = 6;
        Solution solution = new Solution();
        int[] rst = solution.twoSum(num, target);
        System.out.println(rst);
    }


}

package cd.wangyong.leetcode.算法.双指针;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 三数之和等于0
 * 答案中不可以包含重复的三元组
 * @author andy
 * @since 2021/2/3
 */
public class 三数之和 {

    public List<List<Integer>> threeSum(int[] nums) {
        // 边界情况
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }

        // 从小到大排序，方便查找
        Arrays.sort(nums);

        // 若最大3个三个数小于0,则说明不存在
        if (nums[nums.length -3] + nums[nums.length -2] + nums[nums.length -1]  < 0) {
            return Collections.emptyList();
        }

        // 正常处理
        List<List<Integer>> result = new ArrayList<>();
        int maxI = nums.length -3;
        for (int i = 0; i <= maxI; i++) {
            if (nums[i] > 0) {
                break;
            }

            // 避免重复：答案中不可以包含重复的三元组
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 避免nums[i]过小
            if (nums[i] +  nums[nums.length -2] + nums[nums.length -1] < 0) {
                continue;
            }

            // 左右指针，感觉还是顺序查找
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int tmp = nums[i] + nums[l] + nums[r];
                if (tmp == 0) {

                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));

                    while (l < r && nums[l+1] == nums[l]) {
                        l++;
                    }

                    while (l < r && nums[r-1] == nums[r]) {
                        r--;
                    }

                    l++;
                    r--;
                }
                else if (tmp > 0) {
                    r--;
                }
                else {
                    l++;
                }
            }
        }
        return result;
    }
}

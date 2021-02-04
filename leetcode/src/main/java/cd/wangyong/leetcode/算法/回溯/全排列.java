package cd.wangyong.leetcode.算法.回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 全排列 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrace(new LinkedList<>(), nums, result);
        return result;
    }

    /**
     * 辅助函数，回溯函数backtrace
     * @param trace 跟踪标记
     * @param nums 原始输入
     * @param result 结果
     */
    private void backtrace(LinkedList<Integer> trace, int[] nums, List<List<Integer>> result) {
        // 边界处理：递归结束条件
        if (trace.size() == nums.length) {
            result.add(new ArrayList<>(trace));
            return;
        }

        // 遍历，递归本质是树的深度遍历
        for (int num : nums) {
            if (trace.contains(num)) continue;
            trace.add(num);
            backtrace(trace, nums, result);
            trace.removeLast();
        }
    }
}

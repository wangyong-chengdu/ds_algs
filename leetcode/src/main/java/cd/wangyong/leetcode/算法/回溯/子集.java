package cd.wangyong.leetcode.算法.回溯;

import java.util.LinkedList;
import java.util.List;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 子集 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> trace = new LinkedList<>();
        backtrace(trace, nums, 0, result);
        return result;
    }

    private void backtrace(LinkedList<Integer> trace, int[] nums, int startIndex, List<List<Integer>> result) {
        result.add(new LinkedList<>(trace)); // 拷贝trace
        for (int i = startIndex; i < nums.length; i++) {
            trace.add(nums[i]);
            backtrace(trace, nums, i + 1, result);
            trace.removeLast();
        }
    }
}

package cd.wangyong.leetcode.数据结构.二叉堆;

import java.util.PriorityQueue;

/**
 * @author andy
 * @since 2021/2/5
 */
public class 数组中的第K个最大元素 {

    public int findKthLargest(int[] nums, int k) {
        // 构建最大堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(Integer::compare);
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) pq.remove(); // remove最小的
        }
        // 返回最大K个元素中的最小者。
        return pq.remove();
    }
}

package cd.wangyong.da_algs.leetcode.sliding_window.q239_sliding_window_maximum;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author wangyong
 * @since 2020/5/27
 */
public class Solution {
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k == 1) return nums;
        int[] result = new int[nums.length - k + 1];
        int left = 0, right = left + k - 1;
        while (right < nums.length) {
            int max = nums[left];
            for (int i = left + 1; i <= right; i++) {
                if (nums[i] >= max)  max = nums[i];
            }
            result[left++] = max;
            right++;
        }
        return result;
    }

    public int[] maxSlidingWindow3(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k == 1) return nums;
        int[] result = new int[nums.length - k + 1];
        int left = 0, right = left + k - 1;
        int max = Integer.MIN_VALUE;
        while (right < nums.length) {
            // if pass element is the max, then set max to default value.
            if (left > 0 && result[left - 1] == max) {
                max = Integer.MIN_VALUE;
            }
            // if left = 0 or max is the default value then iterate window inside element to get the max
            if (left == 0 || max == Integer.MIN_VALUE) {
                max = nums[left];
                for (int i = left + 1; i <= right; i++) {
                    if (nums[i] >= max)  max = nums[i];
                }
            }
            else if (result[right] >= max) {
                max = result[right];
            }
            result[left++] = max;
            right++;
        }
        return result;
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k == 1) return nums;
        int[] result = new int[nums.length - k + 1];
        LinkedList<Integer> dq = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!dq.isEmpty() && dq.peek() < i - k + 1) {
                dq.poll();
            }
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offer(i);
            if (i - k + 1 >= 0) {
                result[i - k + 1] = nums[dq.peek()];
            }
        }
        return result;
    }

    public void cleanDeque(Deque<Integer> deq, int[] nums, int i, int k) {
        // remove indexes of elements not from sliding window
        if (!deq.isEmpty() && deq.getFirst() == i - k) deq.removeFirst();
        // remove from deq indexes of all elements, which are smaller than current element nums[i]
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) deq.removeLast();
    }

    public int[] maxSlidingWindow0(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (n == 1 || k == 1) return nums;

        // init deque and output
        int maxIndex = 0;
        Deque<Integer> deq = new ArrayDeque<Integer>();
        for (int i = 0; i < k; i++) {
            cleanDeque(deq, nums, i, k);
            deq.addLast(i);
            if (nums[i] > nums[maxIndex]) maxIndex = i;
        }
        int [] output = new int[n - k + 1];
        output[0] = nums[maxIndex];

        // build output
        for (int i  = k; i < n; i++) {
            cleanDeque(deq, nums, i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k <= 0) return new int[0];
        if (nums == null || nums.length <= 1 || k == 1) return nums;

        // Deque<Integer> deque = new ArrayDeque<>(k);
        Deque<Integer> deque = new ArrayDeque<>();
        int[] output = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            cleanDeque(deque, nums, i, k);
            deque.addLast(i);
            if (i - k + 1 >= 0) {
                output[i - k + 1] = deque.getFirst();
            }
        }
        return output;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
    }
}

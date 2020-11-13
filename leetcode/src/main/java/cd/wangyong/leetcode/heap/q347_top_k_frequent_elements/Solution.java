package cd.wangyong.leetcode.heap.q347_top_k_frequent_elements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author wangyong
 * @since 2020/6/10
 */
public class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);

        // minimum heap, which key is the frequency
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer num = entry.getKey();
            Integer frequency = entry.getValue();
            if (pq.size() < k) pq.add(num);
            else if (frequency > map.get(pq.peek())) {
                pq.remove();
                pq.add(num);
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) res[i] = pq.remove();
        return res;
    }
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);

        MaxPQ maxPq = new MaxPQ(map.size() + 1);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            maxPq.insert(new NumCount(entry.getKey(), entry.getValue()));
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) result[i] = maxPq.delMax().num;
        return result;
    }

    class NumCount {
        public int num;
        public int count;

        public NumCount(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    class MaxPQ {
        private NumCount[] pq;
        private int n;

        public MaxPQ(int capacity) {
            pq = new NumCount[capacity];
        }

        public void insert(NumCount one) {
            pq[++n] = one;
            swim(n);
        }

        public NumCount delMax() {
            NumCount max = pq[1];
            exch(1, n--);
            sink(1);
            pq[n + 1] = null;
            return max;
        }

        private void sink(int k) {
            while (2*k <= n) {
                int j = 2*k;
                if (j < n && pq[j].count < pq[j+1].count) j++;
                if (!(pq[k].count < pq[j].count)) break;
                exch(k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && pq[k / 2].count < pq[k].count) {
                exch(k / 2, k);
                k = k / 2;
            }
        }

        private void exch(int i, int j) {
            NumCount swap = pq[i];
            pq[i] = pq[j];
            pq[j] = swap;
        }
    }
}

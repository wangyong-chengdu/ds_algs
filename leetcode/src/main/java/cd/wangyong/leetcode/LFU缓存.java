package cd.wangyong.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author andy
 * @since 2020/12/7
 */
public class LFU缓存 {
    static class LFUCache {
        class Node {
            int key;
            int val;
            int cnt;
            long time;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
                this.cnt = 1;
                this.time = System.currentTimeMillis();
            }

            public void updateAndRefresh(int val) {
                this.val = val;
                refresh();
            }

            public void refresh() {
                cnt++;
                time = System.currentTimeMillis();
            }
        }

        // 容量
        private final int CAP;

        // 最小堆
        private final PriorityQueue<Node> MIN_PQ = new PriorityQueue<>((a, b) -> {
            int res = Integer.compare(a.cnt, b.cnt);
            if (res != 0) return res;
            else return Long.compare(a.time, b.time);
        });

        // 缓存
        private final Map<Integer, Node> MAP = new HashMap<>();

        private int size;

        public LFUCache(int capacity) {
            this.CAP = capacity;
        }

        public int get(int key) {
            Node node = MAP.get(key);
            if (node == null) {
                return -1;
            }
            // 刷新结点
            node.refresh();
            // 更新MIN_PQ
            refreshMinPQ(node);
            return node.val;
        }

        public void put(int key, int value) {
            Node node = MAP.get(key);
            // node存在
            if (node != null) {
                update(node, value);
            }
            // node不存在，则插入
            else {
                insert(key, value);
            }
        }

        private void update(Node node, int value) {
            node.updateAndRefresh(value);
            refreshMinPQ(node);
        }

        private void insert(int key, int value) {
            // 超出容量，则移除最小结点
            if (size == CAP) {
                Node node = MIN_PQ.remove();
                MAP.remove(node.key);
                size--;
            }
            Node insNode = new Node(key, value);
            MAP.put(key, insNode);
            MIN_PQ.add(insNode);
            size++;
        }

        private void refreshMinPQ(Node node) {
            MIN_PQ.remove(node);
            MIN_PQ.add(node);
        }
    }

    public static void main(String[] args) {
        LFUCache lFUCache = new LFUCache(2);
        lFUCache.put(1, 1);
        lFUCache.put(2, 2);
        lFUCache.get(1);      // 返回 1
        lFUCache.put(3, 3);   // 去除键 2
        lFUCache.get(2);      // 返回 -1（未找到）
        lFUCache.get(3);      // 返回 3
        lFUCache.put(4, 4);   // 去除键 1
        lFUCache.get(1);      // 返回 -1（未找到）
        lFUCache.get(3);      // 返回 3
        lFUCache.get(4);      // 返回 4
    }


}

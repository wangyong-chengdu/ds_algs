# LFU 缓存
- https://leetcode-cn.com/problems/lfu-cache/
- 本质：
- 解题思路：用HashMap实现缓存，按照使用次数排序使用链式存储结构维护（平衡二叉树怎样，或者 二叉堆实现呢，优先队列）

## 官网答案

```java
class LFUCache {
    // 缓存容量，时间戳
    int capacity, time;
    Map<Integer, Node> key_table;
    TreeSet<Node> S;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.time = 0;
        key_table = new HashMap<Integer, Node>();
        S = new TreeSet<Node>();
    }
    
    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        // 如果哈希表中没有键 key，返回 -1
        if (!key_table.containsKey(key)) {
            return -1;
        }
        // 从哈希表中得到旧的缓存
        Node cache = key_table.get(key);
        // 从平衡二叉树中删除旧的缓存
        S.remove(cache);
        // 将旧缓存更新
        cache.cnt += 1;
        cache.time = ++time;
        // 将新缓存重新放入哈希表和平衡二叉树中
        S.add(cache);
        key_table.put(key, cache);
        return cache.value;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!key_table.containsKey(key)) {
            // 如果到达缓存容量上限
            if (key_table.size() == capacity) {
                // 从哈希表和平衡二叉树中删除最近最少使用的缓存
                key_table.remove(S.first().key);
                S.remove(S.first());
            }
            // 创建新的缓存
            Node cache = new Node(1, ++time, key, value);
            // 将新缓存放入哈希表和平衡二叉树中
            key_table.put(key, cache);
            S.add(cache);
        } else {
            // 这里和 get() 函数类似
            Node cache = key_table.get(key);
            S.remove(cache);
            cache.cnt += 1;
            cache.time = ++time;
            cache.value = value;
            S.add(cache);
            key_table.put(key, cache);
        }
    }
}

class Node implements Comparable<Node> {
    int cnt, time, key, value;

    Node(int cnt, int time, int key, int value) {
        this.cnt = cnt;
        this.time = time;
        this.key = key;
        this.value = value;
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Node) {
            Node rhs = (Node) anObject;
            return this.cnt == rhs.cnt && this.time == rhs.time;
        }
        return false;
    }

    public int compareTo(Node rhs) {
        return cnt == rhs.cnt ? time - rhs.time : cnt - rhs.cnt;
    }

    public int hashCode() {
        return cnt * 1000000007 + time;
    }
}


```




## 错误题解（不知道哪里存在bug，感觉没问题呀）
```java

class LFUCache {
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
        if (CAP == 0) return;

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

```
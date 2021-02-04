package cd.wangyong.leetcode.数据结构.自定义;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * LFU cache: 根据使用次数进行淘汰，次数相同则根据逻辑时间淘汰掉最久未使用的。
 * @author andy
 * @since 2021/2/3
 */
public class LFUCache {

    /**
     * 缓存容量
     */
    private final int capacity;

    /**
     * 缓存
     */
    private final Map<Integer, Node> cache;

    /**
     * 平衡树
     */
    private final TreeSet<Node> treeSet;

    /**
     * 逻辑时间
     */
    private int time;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.treeSet = new TreeSet<>();
        this.time = 0;
    }

    /**
     * 从缓存中读取
     */
    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        else if (!cache.containsKey(key)) {
            return -1;
        }
        else {
            Node node = getAndRefresh(key);
            return node.value;
        }
    }

    /**
     * 写入缓存
     */
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (cache.containsKey(key)) {
            getAndRefresh(key);
        }
        else {
            // 判断缓存容量上限
            if (cache.size() == capacity) {
                Node outNode = treeSet.first();
                cache.remove(outNode.key);
                treeSet.remove(outNode);
            }
            // 创建新的缓存
            Node cache = new Node(1, ++time, key, value);
            this.cache.put(key, cache);
            treeSet.add(cache);
        }
    }

    private Node getAndRefresh(int key) {
        // 从哈希表中得到旧的缓存
        Node node = cache.get(key);
        // 更新次数
        node.cnt++;
        node.time = ++time;

        // 将结点删除后中心放入缓存和平衡二叉树中
        treeSet.remove(node);
        treeSet.add(node);
        cache.put(key, node);
        return node;
    }
}

/**
 * 结点定义
 */
class Node implements Comparable<Node> {
    /**
     * 使用次数
     */
    int cnt;
    /**
     * 使用时间
     */
    int time;
    /**
     * key
     */
    int key;
    /**
     * 值
     */
    int value;

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

# 合并K个升序链表
- https://leetcode-cn.com/problems/merge-k-sorted-lists/
- 本质：有序链表归并
- 解题思路：1.链表是有递归关系的数据结构；2.有序链表适合使用二路归并方式进行合并[分治思想，分得足够细]
- 应用场景：二路归并（同时两路或多路处理）、加速处理

## 解法
- 思路：利用两个链表归并作为辅助函数，遍历数组两两归并最后得解。
- 时间复杂度：o(k * log2n), 其中k为数据长度、n为数组中链表元素的平均长度。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // 处理边界
        if (lists.length == 0) return null;

        // 以第一个元素为主链表
        ListNode l1 = lists[0];

        // 顺序遍历、两两归并
        for (int i = 1;i < lists.length; i++) {
            l1 = mergeTwoLists(l1, lists[i]);
        }
        return l1;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 边界情况
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        // 正常情况
        if (l1.val < l2.val) {
            ListNode subList = mergeTwoLists(l1.next, l2);
            l1.next = subList;
            return l1;
        }
        else {
            ListNode subList = mergeTwoLists(l1, l2.next);
            l2.next = subList;
            return l2;
        }
    }
}
```

## 解法2
- 思路：顺序遍历两两归并是否还有其他办法可以做到并行多路归并呢？
- 时间复杂度：o(logk * log2n)


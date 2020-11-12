# K 个一组翻转链表
- https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
- 本质：链式存储遍历
- 解题思路：链式存储遍历
- 应用场景：数据处理

## 题解
- 思路：
    - 遍历链表一遍，获取链表长度，根据长度和K决定反转次数
    - 辅助翻转链表片段，可参考反转链表代码
- 时间复杂度：o(n), 空间复杂度:o(1)

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
    public ListNode reverseKGroup(ListNode head, int k) {
        // 边界处理
        if (head == null || head.next == null || k <= 1) return head;

        // 遍历链表，计算链表长度
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }

        int round = len / k;
        ListNode newListHead = null;
        ListNode preTail = null;
        p = head;
        while (round > 0 && p != null) {
            ListNode newHead = reverseList(p, k);
            if (newListHead == null) newListHead = newHead;
            if (preTail != null) preTail.next = newHead;
            
            preTail = p;
            p = p.next;
            round--;
        }
        return newListHead;
    }

    private ListNode reverseList(ListNode head, int k) {
        ListNode prev = null;
        ListNode curr = head;
        
        for (int i = 0; i < k; i++) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        head.next = curr; // 老头结点变成尾结点后指向下一轮的老头结点
        return prev;
    }
}
```
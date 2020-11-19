# 删除链表的倒数第N个节点
- https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
- 本质：链表遍历
- 思路：双指针

## 解法1-双指针
- 思路：pq两指针相差N，当p指向链表末尾时，q正好指向N的倒数N前一个节点
- 时间复杂度o(n)，空间复杂度o(1)

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode p = dummy, q = dummy;

        // p指向顺数第N个结点，构造pq相差N
        for (int i = 1; i <= n; i++) p = p.next;

        while (p.next != null) {
            p = p.next;
            q = q.next;
        }
        q.next = q.next.next;
        return dummy.next;
    }
}
```
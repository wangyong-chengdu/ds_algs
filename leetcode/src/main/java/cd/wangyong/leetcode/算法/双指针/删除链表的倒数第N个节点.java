package cd.wangyong.leetcode.算法.双指针;

import cd.wangyong.leetcode.common.ListNode;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 删除链表的倒数第N个节点 {

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

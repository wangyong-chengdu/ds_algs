package cd.wangyong.leetcode.数据结构.链表;

import cd.wangyong.leetcode.common.ListNode;

/**
 * @author andy
 * @since 2021/2/8
 */
public class 相交链表 {

    /**
     * 链表相交点
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 边界处理
        if (headA == null || headB == null) return null;

        // 遍历到链表a的尾部，将尾部连接到链表a的头部
        ListNode p = headA;
        while (p.next != null) p = p.next;
        p.next = headB;

        // 检查是否有有环，并返回相交点
        ListNode node = detectCycle(headA);

        // 还原
        p.next = null;
        return node;
    }

    /**
     * 链表有环判断
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;

        // 返回快慢指针相遇点
        ListNode intersect = getIntersect(head);
        if (intersect == null) return null;

        // 再重新跑一边，则slow再次与intersect相遇时就是相交点
        ListNode slow = head;
        while (slow != intersect) {
            slow = slow.next;
            intersect = intersect.next;
        }
        return slow;
    }

    /**
     * 快慢指针获取相交点
     */
    private ListNode getIntersect(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return slow;
        }
        return null;
    }
}

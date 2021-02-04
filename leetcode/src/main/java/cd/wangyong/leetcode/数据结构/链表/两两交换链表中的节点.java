package cd.wangyong.leetcode.数据结构.链表;

import cd.wangyong.leetcode.common.ListNode;

/**
 * @author andy
 * @since 2021/2/3
 */
public class 两两交换链表中的节点 {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode nextHead = head.next.next;
        ListNode newHead = head.next;
        newHead.next = head;
        head.next = swapPairs(nextHead);
        return newHead;
    }
}

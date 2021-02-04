package cd.wangyong.leetcode.数据结构.链表;

import cd.wangyong.leetcode.common.ListNode;

/**
 * 迭代法反转
 * @author andy
 * @since 2021/2/3
 */
public class K个一组翻转链表 {

    /**
     * K个一组反转
     */
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

            if (newListHead == null) newListHead = newHead; // 处理整个链表翻转后的头结点
            if (preTail != null) preTail.next = newHead; // 处理上一组翻转后的尾结点

            preTail = p;
            p = p.next;
            round--;
        }
        return newListHead;
    }

    /**
     * 迭代法做链表反转
     */
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

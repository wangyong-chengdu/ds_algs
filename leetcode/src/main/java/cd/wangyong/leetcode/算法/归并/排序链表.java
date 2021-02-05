package cd.wangyong.leetcode.算法.归并;

import cd.wangyong.leetcode.common.ListNode;

/**
 * 给链表排序，要求时间复杂度o(n logn)
 * @author andy
 * @since 2021/2/5
 */
public class 排序链表 {

    public ListNode sortList(ListNode head) {
        // 边界处理
        if (head == null || head.next == null)
            return head;

        // 快慢指针定义，当快指针到达链表尾部时，此时慢指针正好到达链表中间
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 将链表分隔成左右两部分
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        // 两个有序链表的合并
        return mergeTwoLists(left, right);
    }

    // 合并两个有序链表
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 处理边界
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}

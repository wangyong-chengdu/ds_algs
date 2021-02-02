package cd.wangyong.leetcode.数据结构.链表;

import cd.wangyong.leetcode.common.ListNode;

/**
 * 链式结构，递归合并
 * @author andy
 * @since 2021/2/2
 */
public class 合并两个有序链表 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 边界情况
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 正常情况
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

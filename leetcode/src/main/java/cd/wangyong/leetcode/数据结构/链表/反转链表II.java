package cd.wangyong.leetcode.数据结构.链表;

import cd.wangyong.leetcode.common.ListNode;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 反转链表II {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 顺序遍历链表，查找到第m个位置
        ListNode p = head, pre = null;
        int cnt = 1;
        while (cnt < m) {
            pre = p;
            p = p.next;
            cnt++;
        }

        // 计算需要递归的次数
        int round = n - m;
        ListNode rangeHead = reverseList(p, round);
        if (pre != null) {
            pre.next = rangeHead;
            return head;
        }
        // m = 1的情况，即pre = null
        else {
            return rangeHead;
        }
    }

    private ListNode reverseList(ListNode head, int round) {
        if (round == 0 || head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next, --round);
        ListNode tmp = head.next.next;
        head.next.next = head;
        head.next = tmp; // 尾部永远指向需要反转之外的next节点；
        return newHead;
    }
}

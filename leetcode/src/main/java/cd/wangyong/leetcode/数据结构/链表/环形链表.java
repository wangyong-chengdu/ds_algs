package cd.wangyong.leetcode.数据结构.链表;

import cd.wangyong.leetcode.common.ListNode;

/**
 * @author andy
 * @since 2021/2/8
 */
public class 环形链表 {

    /**
     * 如果链表没环，则快指针一定会先到达null
     * 如果链表有环，则慢指针一定有等于快指针的时候
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head, fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}

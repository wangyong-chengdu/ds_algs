package cd.wangyong.leetcode.数据结构.链表;

import cd.wangyong.leetcode.common.ListNode;

/**
 * @author andy
 * @since 2021/2/2
 */
public class 反转链表 {
    /**
     * 链表结构，递归求解（递归本质是不是深度遍历的意思呢）
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next); // p永远指向最后一个节点
        head.next.next = head;
        head.next = null;
        return p;
    }

    /**
     * 迭代法：（其实是不是有点顺序遍历的意思）
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}

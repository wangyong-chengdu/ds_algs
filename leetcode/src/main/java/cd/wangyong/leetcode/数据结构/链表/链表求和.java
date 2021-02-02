package cd.wangyong.leetcode.数据结构.链表;

import java.util.Arrays;

import cd.wangyong.leetcode.common.ListNode;
import cd.wangyong.leetcode.common.Util;

/**
 * 逆向存储，第一个元素是个位
 * @author andy
 * @since 2021/2/2
 */
public class 链表求和 {

    /**
     * 是否可以破坏原链表？ 如果可以，可以节省内存空间，否则需要新建
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1, l2, 0);
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2, int tmp) {
        if (l1 == null && l2 == null && tmp > 0) return new ListNode(tmp);
        else if (l1 == null) return l2;
        else if (l2 == null) return l1;

        int sum = l1.val + l2.val + tmp;
        ListNode head = new ListNode(sum % 10);
        head.next = addTwoNumbers(l1.next, l2.next, sum / 10);
        return head;
    }

    public static void main(String[] args) {
        链表求和 instance = new 链表求和();
//        ListNode list = instance.addTwoNumbers(Util.buildList(Arrays.asList(7, 1, 6)), Util.buildList(Arrays.asList(5, 9, 2)));
        ListNode list = instance.addTwoNumbers(Util.buildList(Arrays.asList(9, 7, 8)), Util.buildList(Arrays.asList(6, 8, 5)));
        Util.printList(list);
    }
}

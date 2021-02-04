package cd.wangyong.leetcode.数据结构.栈;

import java.util.Arrays;
import java.util.Stack;

import cd.wangyong.leetcode.common.ListNode;
import cd.wangyong.leetcode.common.Util;

/**
 * 正向存储，第一个元素是高位
 * @author andy
 * @since 2021/2/2
 */
public class 链表求和2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        else if (l2 == null) return l1;

        Stack<ListNode> st1 = new Stack<>();
        ListNode p = l1;
        while (p != null) {
            st1.push(p);
            p = p.next;
        }

        Stack<ListNode> st2 = new Stack<>();
        p = l2;
        while (p != null) {
            st2.push(p);
            p = p.next;
        }

        ListNode tmp = new ListNode(0);
        while (!st1.isEmpty() && !st2.isEmpty()) {
            ListNode node1 = st1.pop();
            ListNode node2 = st2.pop();
            int sum = node1.val + node2.val + tmp.val;
            tmp.val = sum % 10;
            ListNode prev = new ListNode(sum / 10);
            prev.next = tmp;
            tmp = prev;
        }

        Stack<ListNode> st = st1.isEmpty() ? st2 : st1;
        while (!st.isEmpty()) {
            ListNode node = st.pop();
            int sum = node.val + tmp.val;
            tmp.val = sum % 10;
            ListNode prev = new ListNode(sum / 10);
            prev.next = tmp;
            tmp = prev;
        }

        return tmp.val == 0 ? tmp.next : tmp;
    }

    public static void main(String[] args) {
        链表求和2 instance = new 链表求和2();
//        ListNode list = instance.addTwoNumbers(Util.buildList(Arrays.asList(6, 1, 7)), Util.buildList(Arrays.asList(2, 9, 5)));
//        ListNode list = instance.addTwoNumbers(Util.buildList(Arrays.asList(8, 7, 9)), Util.buildList(Arrays.asList(5, 8, 6)));
        ListNode list = instance.addTwoNumbers(Util.buildList(Arrays.asList(9, 9, 8, 7, 9)), Util.buildList(Arrays.asList(5, 8, 6)));
        Util.printList(list);
    }
}

package cd.wangyong.leetcode.数据结构.链表;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cd.wangyong.leetcode.common.ListNode;
import cd.wangyong.leetcode.common.Util;

/**
 * @author andy
 * @since 2020/12/11
 */
public class 奇数位升序偶数位降序的单向无环链表升序排序 {


    /**
     * 时间复杂度o(n)，空间复杂度o(1)
     */
    public ListNode sortListAsc(ListNode head) {
        if (head == null || head.next == null) return head;

        List<ListNode> oddAndEvenList = splitOddAndEven(head);
        return mergeSortedList(oddAndEvenList.get(0), reverseList(oddAndEvenList.get(1)));
    }

    /**
     * 将链表分离成奇偶链表
     * 时间复杂度：o(n)
     * 空间复杂度：o(1)
     */
    public List<ListNode> splitOddAndEven(ListNode head) {
        if (head == null || head.next == null) return Collections.singletonList(head);

        ListNode evenHead = head.next;
        ListNode odd = head, oddNext = odd.next.next;
        ListNode even = head.next, evenNext = (even.next == null ? null : even.next.next);
        while (true) {
            odd.next = oddNext;
            odd = oddNext;

            if (evenNext == null) break;
            even.next = evenNext;
            even = evenNext;

            oddNext = odd.next.next;
            evenNext = even.next.next;
        }
        odd.next = null;
        even.next = null;

        return Arrays.asList(head, evenHead);
    }

    /**
     * 翻转链表
     * o(n)
     */
    public ListNode reverseList(ListNode head) {
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

    /**
     * 合并两个升序链表
     */
    public ListNode mergeSortedList(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeSortedList(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeSortedList(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) throws IOException {
        List<Integer> inputs = Util.readString2List();
        ListNode head = Util.buildList(inputs);
        ListNode sortedList = new 奇数位升序偶数位降序的单向无环链表升序排序().sortListAsc(head);
        Util.printList(sortedList);
    }
}

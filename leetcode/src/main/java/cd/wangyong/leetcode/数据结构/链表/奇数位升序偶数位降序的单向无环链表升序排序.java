package cd.wangyong.leetcode.数据结构.链表;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author andy
 * @since 2020/12/11
 */
public class 奇数位升序偶数位降序的单向无环链表升序排序 {

    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

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
     */
    public List<ListNode> splitOddAndEven(ListNode head) {
        if (head == null || head.next == null) return Collections.singletonList(head);

        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        ListNode oddNext = odd.next.next, evenNext;
        while (oddNext != null) {
            odd.next = oddNext;
            odd = oddNext;

            evenNext = even.next.next;
            if (evenNext == null) break;
            even.next = evenNext;
            even = evenNext;

            oddNext = odd.next.next;
        }
        odd.next = null;
        even.next = null;
        return Arrays.asList(head, evenHead);
    }

    /**
     * 翻转链表
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
        List<Integer> inputs = readString2List();
        ListNode head = buildList(inputs);
        ListNode sortedList = new 奇数位升序偶数位降序的单向无环链表升序排序().sortListAsc(head);
        printList(sortedList);
    }

    /**
     * 读取输入，生成List
     */
    public static List<Integer> readString2List() throws IOException {
        // 创建一个BufferedReader对象
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 读取第一行数据
        String line = br.readLine().trim();
        return Arrays.stream(line.split("->")).map(Integer::valueOf).collect(Collectors.toList());
    }

    /**
     * 读取输入，构建List
     */
    public static ListNode buildList(List<Integer> inputs) {
        if (inputs == null || inputs.size() == 0) return null;
        ListNode head = new ListNode(inputs.get(0));
        ListNode p = head, q;
        for (int i = 1; i < inputs.size(); i++) {
            q = new ListNode(inputs.get(i));
            p.next = q;
            p = q;
        }
        return head;
    }

    /**
     * 打印单链表
     */
    public static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append("->");
            head = head.next;
        }
        if (sb.length() == 0) {
            System.out.println("");
        }
        else {
            System.out.println(sb.substring(0, sb.length() - 2));
        }
    }
}

package cd.wangyong.da_algs.leetcode.linked_list.q19_remove_nth_node_from_end_of_list;

/**
 * @author wangyong
 * @since 2020/7/3
 */
public class Solution {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("");
            ListNode p = this;
            while (p != null) {
                sb.append(p.val).append("->");
                p = p.next;
            }
            return sb.length() == 0 ? sb.toString() : sb.substring(0, sb.length() - 2);
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 1.compute the length of list
        int len = 0;
        for (ListNode p = head; p != null; p = p.next) len++;

        int m = len - n;
        int i = 0;
        ListNode p = head, pre = null;
        while (p != null && i++ < m) {
            pre = p;
            p = p.next;
        }

        if (pre == null) return null;
        pre.next = p.next;
        return head;
    }
}

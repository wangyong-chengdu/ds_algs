package cd.wangyong.leetcode;

/**
 * @author andy
 * @since 2020/11/14
 */
public class 合并K个升序链表 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 递归归并思想求解
    public ListNode mergeKLists(ListNode[] lists) {
        // 处理边界
        if (lists.length == 0) return null;
        return mergeLists(lists, 0, lists.length - 1);
    }

    // 辅助函数
    private ListNode mergeLists(ListNode[] lists, int lo, int hi) {
        // 递归结束条件，分无可分
        if (hi <= lo) return lists[lo];

        int mid = lo + (hi - lo) / 2;
        ListNode left = mergeLists(lists, lo, mid);
        ListNode right = mergeLists(lists, mid + 1, hi);
        return mergeTwoLists(left, right);
    }

    // 归并两个链表
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 边界情况
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 正常情况
        if (l1.val < l2.val) {
            ListNode subList = mergeTwoLists(l1.next, l2);
            l1.next = subList;
            return l1;
        } else {
            ListNode subList = mergeTwoLists(l1, l2.next);
            l2.next = subList;
            return l2;
        }
    }
}

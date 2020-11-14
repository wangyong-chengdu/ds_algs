# 反转链表 II
- https://leetcode-cn.com/problems/reverse-linked-list-ii/
- 本质：链表顺序遍历
- 解题思路：链表是具有递归属性的数据结构，通过递归实现链表反转
- 应用场景：数据处理，讲求高效

## 解法
- 解法：同反转链表，利用单链表递归数据结构使用递归方式实现
- 时间复杂度：o(n)
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
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
```



package cd.wangyong.da_algs.leetcode.linked_list.q2.add_two_numbers;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author wangyong
 * @since 2020/05/26
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

    /**
     * 乘法思路，数据会越变越大 bad
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        BigInteger sum = new BigInteger("0");
        long n = 0;
        ListNode p = l1;
        while (p != null) {
            sum = sum.add(new BigDecimal(String.valueOf(Math.pow(10.0, n) * p.val)).toBigInteger());
            p = p.next;
            n++;
        }

        long n2 = 0;
        p = l2;
        while (p != null) {
            sum = sum.add(new BigDecimal(String.valueOf(Math.pow(10.0, n2) * p.val)).toBigInteger());
            p = p.next;
            n2++;
        }

        ListNode l3 = null, pre = null, node = null;
        do {
            BigInteger[] divideAndRemainder = sum.divideAndRemainder(BigInteger.TEN);
            node = new ListNode(divideAndRemainder[1].intValue());
            if (l3 == null) {
                l3 = node;
            }
            if (pre != null) {
                pre.next = node;
            }
            pre = node;
            sum = divideAndRemainder[0];
        } while (sum.compareTo(BigInteger.ZERO) > 0);
        return l3;
    }

    /**
     * 一个一个计算，除法思路
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0;
        ListNode l3 = null, pre = null, node = null;

        while (l1 != null || l2 != null || sum != 0) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            node = new ListNode(sum % 10);
            if (l3 == null) l3 = node;
            if (pre != null) pre.next = node;
            pre = node;
            sum /= 10;
        }
        return l3;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.addTwoNumbers(ofListNode("1->8"), ofListNode("0")));
//        System.out.println(solution.addTwoNumbers(ofListNode("2->4->3"), ofListNode("5->6->4")));
//        System.out.println(solution.addTwoNumbers(ofListNode("9"), ofListNode("1->9->9->9->9->9->9->9->9->9")));
        System.out.println(solution.addTwoNumbers2(of("[1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]"), of("[5,6,4]")));
        System.out.println(solution.addTwoNumbers2(of("[2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,9]"),
                of("[5,6,4,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,9,9,9,9]")));

        System.out.println(solution.addTwoNumbers(of("[2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,9]"),
                of("[5,6,4,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,2,4,3,9,9,9,9]")));
    }

    private static ListNode ofListNode(String listStr) {
        String[] split = listStr.split("->");
        ListNode l = null, pre = null, node = null;
        for (String ele : split) {
            node = new ListNode(Integer.parseInt(ele));
            if (l == null) l = node;
            if (pre != null) pre.next = node;
            pre = node;
        }
        return l;
    }

    private static ListNode of(String listStr) {
        String[] split = listStr.substring(1, listStr.length() - 1).split(",");
        ListNode l = null, pre = null, node = null;
        for (String ele : split) {
            node = new ListNode(Integer.parseInt(ele));
            if (l == null) l = node;
            if (pre != null) pre.next = node;
            pre = node;
        }
        return l;
    }

}

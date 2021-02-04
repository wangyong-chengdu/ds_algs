package cd.wangyong.leetcode.算法.归并;

import cd.wangyong.leetcode.common.Util;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 两个有序数组合并 {

    public int[] mergeTwoSortedArray(int[] a, int lenA, int[] b) {
        int n = b.length, len = lenA + n;
        if (a[lenA - 1] <= b[0]) {
            System.arraycopy(b, 0, a, lenA, n);
            return a;
        }
        else if (a[0] >= b[n - 1]) {
            moveSteps(a, lenA - 1, n);
            System.arraycopy(b, 0, a, 0, n);
            return a;
        }
        else {
            int i = lenA - 1, j = n - 1, k = len - 1;
            while (i >= 0 && j >= 0) {
                if (a[i] > b[j]) {
                    a[k--] = a[i--];
                }
                else {
                    a[k--] = b[j--];
                }
            }
            while (j >= 0) {
                a[k--] = b[j--];
            }
        }
        return a;
    }

    private static void moveSteps(int[] a, int endIndex, int n) {
        for (int i = endIndex; i >= 0; i--) {
            a[i + n] = a[i];
        }
    }

    public static void main(String[] args) {
        两个有序数组合并 instance = new 两个有序数组合并();
        Util.printArray(instance.mergeTwoSortedArray(new int[] {1, 2, 3, 0, 0}, 3, new int[] {2, 6}));
        Util.printArray(instance.mergeTwoSortedArray(new int[] {1, 2, 3, 0, 0}, 3, new int[] {4, 6}));
        Util.printArray(instance.mergeTwoSortedArray(new int[] {8, 9, 10, 0, 0}, 3, new int[] {4, 6}));
    }
}

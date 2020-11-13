package cd.wangyong.leetcode.q767_reorganize_string;

import java.util.Arrays;

/**
 * @author wangyong
 * @since 2020/6/10
 */
public class Solution {

    public String reorganizeString(String S) {
        char[] charArray = S.toCharArray();
        Arrays.sort(charArray);

        for (int i = 1; i < charArray.length; i++) {
            // count repleat char
            int repeatCount = 0;
            while (i < charArray.length && charArray[i] == charArray[i - 1]) {
                repeatCount++;
                i++;
            }

            // partition repleat char
            while (i < charArray.length && repeatCount > 0) {
                exch(charArray, i, i - repeatCount);
                i++;
                repeatCount--;
            }

            if (repeatCount > 0) return "";
        }
        return new String(charArray);
    }

    private void exch(char[] a, int i, int j) {
        char swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}

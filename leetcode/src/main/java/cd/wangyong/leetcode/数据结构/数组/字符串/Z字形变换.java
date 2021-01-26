package cd.wangyong.leetcode.数据结构.数组.字符串;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andy
 * @since 2021/1/26
 */
public class Z字形变换 {

    public String convert(String s, int numRows) {
        assert numRows > 0 && s != null && s.length() > 0;
        if (numRows == 1) return s;

        int len = s.length();
        int period = (numRows - 2) * 2 + 2;
        List<StringBuilder> res = new ArrayList<>(period);
        for (int i = 0; i < period && i < len; i++) {
            StringBuilder sb = new StringBuilder();
            res.add(sb);

            for (int j = i; j < len; j += period) {
                sb.append(s.charAt(j));
            }
        }

        int bottomFirst = numRows - 1;
        int i = bottomFirst - 1;
        int j = bottomFirst + 1;
        while (i > 0 && j < period) {
            StringBuilder sb = mergeStringBuilder(res.get(i), res.get(j));
            res.remove(i);
            res.add(i, sb);
            i--;
            j++;
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k <= bottomFirst; k++) {
            sb.append(res.get(k));
        }
        return sb.toString();
    }

    private StringBuilder mergeStringBuilder(StringBuilder sb1, StringBuilder sb2) {
        StringBuilder sb = new StringBuilder();
        int len = Math.max(sb1.length(), sb2.length());
        for (int i = 0; i < len; i++) {
            if (i < sb1.length()) sb.append(sb1.charAt(i));
            if (i < sb2.length()) sb.append(sb2.charAt(i));
        }
        return sb;
    }

    public static void main(String[] args) {
        Z字形变换 instance = new Z字形变换();
        String res = instance.convert("PAYPALISHIRING", 3);
        System.out.println(res);
        assert res.equals("PAHNAPLSIIGYIR");

        String res2 = instance.convert("PAYPALISHIRING", 4);
        System.out.println(res2);
        assert res.equals("PINALSIGYAHRPI");
    }

}

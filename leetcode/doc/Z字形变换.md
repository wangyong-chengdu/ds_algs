# Z 字形变换

- https://leetcode-cn.com/problems/zigzag-conversion/
- 本质：数组遍历
- 解法：利用数组随机访问时间复杂度为o(1),

## 解法

- 时间复杂度：o(n)
- 空间复杂读：o(n)


```java

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


```
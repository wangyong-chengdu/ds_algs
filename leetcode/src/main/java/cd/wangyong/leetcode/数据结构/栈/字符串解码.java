package cd.wangyong.leetcode.数据结构.栈;

/**
 * @author andy
 * @since 2021/2/8
 */
public class 字符串解码 {
    private int i;
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return s;

        i = 0;
        return dfs(s);
    }

    private String dfs(String s) {
        StringBuilder sub = new StringBuilder();

        while (i < s.length()) {
            // 数字处理
            StringBuilder digit = new StringBuilder();
            while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                digit.append(s.charAt(i++));
            }

            if (digit.length() > 0) {
                int cnt = Integer.parseInt(digit.toString());
                this.i++; // 过滤掉[
                String decode = dfs(s);
                while (cnt-- > 0) sub.append(decode);
            }

            // ]处理
            if (i < s.length() && s.charAt(i) == ']') {
                i++;
                break;
            }

            // 其他字符处理
            while (i < s.length() && s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                sub.append(s.charAt(i++));
            }
        }
        return sub.toString();
    }

    public static void main(String[] args) {
        字符串解码 instance = new 字符串解码();

        System.out.println(instance.decodeString("3[a]2[bc]"));
        System.out.println(instance.decodeString("3[a2[c]]"));
        System.out.println(instance.decodeString("2[abc]3[cd]ef"));
        System.out.println(instance.decodeString("abc3[cd]xyz"));
    }
}

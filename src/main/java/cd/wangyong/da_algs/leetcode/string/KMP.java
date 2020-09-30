package cd.wangyong.da_algs.leetcode.string;

/**
 * @author wangyong
 * @since 2020/7/2
 */
public class KMP {
    public static int search(String txt, String pattern) {
        int[][] dfa = buildDfa(pattern);
        int i, j, N = txt.length(), M = pattern.length();
        for (i = 0, j = 0; i < N && j < M; i++)
            j = dfa[txt.charAt(i)][j];
        if (j == M) return i - M;
        else return N;
    }

    private static int[][] buildDfa(String pattern) {
        int R = 256; // 256个字母
        int[][] dfa = new int[R][pattern.length()];
        dfa[pattern.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < pattern.length(); j++) {
            // 复制匹配失败下的值
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][x];
            // 设置匹配成功情况下的值
            dfa[pattern.charAt(j)][j] = j + 1;
            x = dfa[pattern.charAt(j)][x];
        }
        return dfa;
    }

    public static int match(String s, String p) {
        int[] next = buildNext(p);
        int m = s.length(), n = p.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (j < 0 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            }
            else j = next[j];
        }
        return i - j;
    }

    private static int[] buildNext(String p) {
        int[] next = new int[p.length()];
        int j = 0;
        int t = next[0] = - 1; // t是未匹配上需要返回的索引

        while (j < p.length() - 1) {
            // 匹配成功
            if (t < 0 || p.charAt(j) == p.charAt(t)) {
                j++;
                t++;
                next[j] = t;
            }
            else t = next[t];
        }
        return next;
    }

    public static void main(String[] args) {
//        int[] res = buildNext("ACTGPACY");
//        System.out.println(res);
//        System.out.println(search("", ));

//        int[] res = buildNext("AAAAG");
//        System.out.println(res);
    }


}

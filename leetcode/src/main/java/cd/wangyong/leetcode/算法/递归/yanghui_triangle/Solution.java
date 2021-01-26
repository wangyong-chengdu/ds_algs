package cd.wangyong.leetcode.算法.递归.yanghui_triangle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wangyong
 * @since 2020/6/24
 */
public class Solution {

    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) return Collections.emptyList();

        int[][] mem = new int[rowIndex + 1][rowIndex + 1];
        List<Integer> res = new ArrayList<>(rowIndex + 1);
        for (int j = 0; j <= rowIndex; j++)
            res.add(f(rowIndex, j, mem));
        return res;
}

    private int f(int i, int j, int[][] mem) {
        if (mem[i][j] != 0) return mem[i][j];
        if (j == 0 || j == i) {
            mem[i][j] = 1;
            return mem[i][j];
        }
        mem[i][j] = f(i - 1, j - 1, mem) + f(i - 1, j, mem);
        return mem[i][j];
    }
}

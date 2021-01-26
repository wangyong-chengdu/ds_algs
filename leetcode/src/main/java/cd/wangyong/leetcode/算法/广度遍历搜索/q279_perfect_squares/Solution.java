package cd.wangyong.leetcode.算法.广度遍历搜索.q279_perfect_squares;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wangyong
 * @since 2020/6/27
 */
public class Solution {
    public int numSquares(int n) {
        if (n < 1) return -1;
        List<Integer> squareNums = computeSquareNums(n);
        return bfs(n, squareNums);
    }

    private List<Integer> computeSquareNums(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i * i <= n; i++)
            list.add(i * i);
        return list;
    }

    private int bfs(int n, List<Integer> squareNums) {
        Set<Integer> set = new HashSet<>();
        set.add(n);
        int level = 0;

        while (!set.isEmpty()) {
            level++;
            Set<Integer> tmpSet = new HashSet<>();
            for (Integer remainder : set) {
                for (Integer squareNum : squareNums) {
                    if (remainder.equals(squareNum)) return level;
                    else if (remainder < squareNum) break;
                    else tmpSet.add(remainder - squareNum);
                }
            }
            set = tmpSet;
        }
        return level;
    }


}

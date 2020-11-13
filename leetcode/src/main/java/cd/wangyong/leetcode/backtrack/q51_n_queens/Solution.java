package cd.wangyong.leetcode.backtrack.q51_n_queens;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangyong
 * @since 2020/6/3
 */
public class Solution {

    public List<List<String>> solveNQueens(int n) {
        LinkedList<String> board = new LinkedList<>();
        List<Integer> choises = initChoises(n);
        List<List<String>> result = new LinkedList<>();
        backtrace(board, choises, 0, n, result);
        return result;
    }

    private List<Integer> initChoises(int n) {
        List<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) list.add(i);
        return list;
    }

    private String genLine(Integer queenPos, Integer n) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < n; j++) sb.append(j == queenPos ? "Q" : ".");
        return sb.toString();
    }

    private void backtrace(LinkedList<String> board, List<Integer> choises, int row, int n, List<List<String>> result) {
        if (board.size() == n) {
            result.add(new LinkedList(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!isValid(board, choises, row, col, n)) continue;
            board.add(genLine(col, n));
            choises.remove(Integer.valueOf(col));
            backtrace(board, choises, row + 1, n, result);
            choises.add(col);
            board.removeLast();
        }
    }

    private boolean isValid(List<String> board, List<Integer> choises, int row, int col, int n) {
        if (!choises.contains(col)) return false;

        int rowLeft = row, colLeft = col;
        while(--rowLeft >= 0 && --colLeft >= 0)
            if (board.get(rowLeft).charAt(colLeft) == 'Q') return false;

        int rowRight = row, colRight = col;
        while(--rowRight >= 0 && ++colRight < n)
            if (board.get(rowRight).charAt(colRight) == 'Q') return false;

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solveNQueens(4));
    }

}

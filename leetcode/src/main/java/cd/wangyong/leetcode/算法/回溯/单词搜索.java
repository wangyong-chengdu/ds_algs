package cd.wangyong.leetcode.算法.回溯;

import java.util.LinkedList;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 单词搜索 {
    private int n; // 行数
    private int m; // 列数
    private String word; // 要查找的单词
    private int wordLen; // 单词长度

    public boolean exist(char[][] board, String word) {
        // 边界处理
        if (board.length == 0 || word == null || word.length() == 0) return false;

        this.n = board.length;
        this.m = board[0].length;
        this.word = word;
        this.wordLen = word.length();

        boolean[][] mark = new boolean[this.n][this.m];

        LinkedList<Character> trace = new LinkedList<>();
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                boolean res = backtrace(trace, 0, mark, board, i, j);
                if (res) return true;
            }
        }
        return false;
    }


    private boolean backtrace(LinkedList<Character> trace, int index, boolean[][] mark, char[][] board, int row, int col) {
        // 边界处理
        // 大于字符串最大长度，说明遍历完了但是找不到
        if (index == wordLen) return false;
        // 字符不匹配
        if (word.charAt(index) != board[row][col]) return false;
        // 已经标记过
        if (mark[row][col]) return false;
        // 找到了
        if (index == wordLen - 1 && word.charAt(index) == board[row][col]) return true;

        // 正常处理

        // 跟踪字符
        trace.add(board[row][col]);
        mark[row][col] = true;

        // 遍历选择
        boolean isFound = false;

        // 向上查找
        if (row - 1 >= 0) isFound = backtrace(trace, index + 1, mark, board, row - 1, col);
        if (isFound) return true;

        // 向下查找
        if (row + 1 < this.n) isFound = backtrace(trace, index + 1, mark, board, row + 1, col);
        if (isFound) return true;

        // 向左查找
        if (col - 1 >= 0) isFound = backtrace(trace, index + 1, mark, board, row, col - 1);
        if (isFound) return true;

        // 向右查找
        if (col + 1 < this.m) isFound = backtrace(trace, index + 1, mark, board, row, col + 1);
        if (isFound) return true;

        // 回退
        trace.removeLast();
        mark[row][col] = false;
        return false;
    }
}

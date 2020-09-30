package cd.wangyong.da_algs.leetcode.backtrack.q79_word_search;

import java.util.LinkedList;

public class Solution {
    private int n;
    private int m;
    private String word;
    private int wordLen;


    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || word == null || word.length() == 0) return false;
        this.n = board.length;
        this.m = board[0].length;
        this.word = word;
        this.wordLen = word.length();
        boolean[][] mark = new boolean[this.n][this.m];

        LinkedList<Character> track = new LinkedList<>();
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                boolean res = backtrack(track, 0, mark, board, i, j);
                if (res) return true;
            }
        }
        return false;
    }

    private boolean backtrack(LinkedList<Character> track, int index, boolean[][] mark, char[][] board, int row, int col) {
        // recursion end condition index大于字符串最大长度 + 字符不匹配 + 已标记过
        if (index == wordLen) return false;
        if (word.charAt(index) != board[row][col]) return false;
        if (mark[row][col]) return false;
        if (index == wordLen - 1 && word.charAt(index) == board[row][col]) return true;

        // add track
        track.add(board[row][col]);
        mark[row][col] = true;

        // 遍历选择
        boolean isFound = false;
        if (row - 1 >= 0) isFound = backtrack(track, index + 1, mark, board, row - 1, col);
        if (isFound) return true;

        if (row + 1 < this.n) isFound = backtrack(track, index + 1, mark, board, row + 1, col);
        if (isFound) return true;

        if (col - 1 >= 0) isFound = backtrack(track, index + 1, mark, board, row, col - 1);
        if (isFound) return true;

        if (col + 1 < this.m) isFound = backtrack(track, index + 1, mark, board, row, col + 1);
        if (isFound) return true;

        // 回退 track
        track.removeLast();
        mark[row][col] = false;
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCCED");
        solution.exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCB");
    }
}

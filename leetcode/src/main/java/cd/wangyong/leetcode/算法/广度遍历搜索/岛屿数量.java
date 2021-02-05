package cd.wangyong.leetcode.算法.广度遍历搜索;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author andy
 * @since 2021/2/5
 */
public class 岛屿数量 {

    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;

        int rowNum = grid.length;
        int colNum = grid[0].length;
        int count = 0;

        Queue<Integer> queue = new LinkedList<>();
        for (int r = 0; r < rowNum; r++)
            for (int c = 0; c <  colNum; c++)
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    count++;
                    bfs(grid, r, c, queue); // bfs以grid[r][c]为原点遍历消灭岛屿
                }
        return count;
    }

    private void bfs(char[][] grid, int rowIndex, int colIndex, Queue<Integer> queue) {
        int rowNum = grid.length;
        int colNum = grid[0].length;

        queue.add(rowIndex * colNum + colIndex);
        while (!queue.isEmpty()) {
            // 还原行号、列号
            int id = queue.remove();
            int row = id / colNum;
            int col = id % colNum;

            // 上
            if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                queue.add((row - 1) * colNum + col);
                grid[row - 1][col] = '0';
            }
            // 下
            if (row + 1 < rowNum && grid[row + 1][col] == '1') {
                queue.add((row + 1) * colNum + col);
                grid[row + 1][col] = '0';
            }
            // 左
            if (col - 1 >=0 && grid[row][col - 1] == '1') {
                queue.add((row) * colNum + col - 1);
                grid[row][col - 1] = '0';
            }
            // 右
            if (col + 1 < colNum && grid[row][col + 1] == '1') {
                queue.add((row) * colNum + col + 1);
                grid[row][col + 1] = '0';
            }
        }
    }
}

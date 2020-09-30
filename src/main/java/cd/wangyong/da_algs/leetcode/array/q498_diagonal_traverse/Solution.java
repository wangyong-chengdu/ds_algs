package cd.wangyong.da_algs.leetcode.array.q498_diagonal_traverse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author wangyong
 * @since 2020/7/1
 */
public class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        List<Integer> res = new ArrayList<>();

        int row = matrix.length;
        int col = matrix[0].length;
        int rowIndex = 0, colIndex = 0;
        int loopCount = 0;
        Stack<Integer> stack = new Stack<>(); // 当循环为奇数时，需要借助stack按对角线逆向顺序输出元素。

        // 对角线遍历
        while (true) {
            // 循环截止点
            if (rowIndex == row - 1 && colIndex == col - 1) {
                res.add(matrix[rowIndex][colIndex]);
                break;
            }
            // 用于保存每次对角线循环的起始位置
            int tempRow = rowIndex, tempCol = colIndex;
            loopCount++; // 用于计算循环次数
            int remainder = loopCount % 2;

            while (rowIndex >= 0 && rowIndex < row && colIndex >= 0 && colIndex < col) {
                if (remainder == 1) stack.push(matrix[rowIndex][colIndex]);
                else res.add(matrix[rowIndex][colIndex]);
                rowIndex++;
                colIndex--;
            }
            while (!stack.isEmpty()) res.add(stack.pop());

            if (tempCol + 1 < col) {
                rowIndex = tempRow;
                colIndex = tempCol + 1;
            }
            else {
                rowIndex = tempRow + 1;
                colIndex = tempCol;
            }
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }
}

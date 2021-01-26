package cd.wangyong.leetcode.数据结构.数组.二分查找.q240;

/**
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/?utm_source=LCUS&utm_medium=ip_redirect_q_uns&utm_campaign=transfer2china
 * @author wanygong
 * @since 2020/4/24
 */
public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int i = 0;
        int j = matrix[0].length - 1;

        while (i <  matrix.length && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            else if (matrix[i][j] > target) {
                j--;
            }
            else {
                i++;
            }
        }
        return false;
    }

    public static void testCase1(String[] args) {
        int[][] matrix = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        Solution solution = new Solution();
        System.out.println(solution.searchMatrix(matrix, 5));
        System.out.println(solution.searchMatrix(matrix, 20));
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,1}};
        Solution solution = new Solution();
        System.out.println(solution.searchMatrix(matrix, 2));
    }
}

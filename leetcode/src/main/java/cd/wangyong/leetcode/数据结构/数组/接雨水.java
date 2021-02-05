package cd.wangyong.leetcode.数据结构.数组;

/**
 * @author andy
 * @since 2021/2/5
 */
public class 接雨水 {

    /**
     * 针对每一列从两边查找最大柱子(maxLeft and maxRight)，左右最大柱子的最小者减去柱高就是该列的容积。
     */
    public int trap(int[] height) {
        int ans = 0;
        int size = height.length;

        // 遍历数组
        for (int i = 1; i < size - 1; i++) {
            int maxLeft = 0, maxRight = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                maxLeft = Math.max(maxLeft, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                maxRight = Math.max(maxRight, height[j]);
            }
            ans += Math.min(maxLeft, maxRight) - height[i];
        }
        return ans;
    }
}

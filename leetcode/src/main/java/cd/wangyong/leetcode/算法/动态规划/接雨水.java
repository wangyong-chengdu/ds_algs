package cd.wangyong.leetcode.算法.动态规划;

/**
 * @author andy
 * @since 2021/2/5
 */
public class 接雨水 {

    /**
     * 所谓笨方法就是时间效率很低。为了查找到左右两边的最大值，我们每次都需要重新查找一遍。
     * 有没有什么办法可以提前存储呢？是不是大家想到了动态规划，基本计算思路还是沿用解法1,只是提前计算好最大值。
     */
    public int trap(int[] height) {
        // 边界处理
        if (height == null || height.length == 0)
            return 0;

        int size = height.length;
        int[] maxLeft = new int[size];
        int[] maxRight = new int[size];
        maxLeft[0] = height[0];

        // 计算每个位置左边的最大值
        for (int i = 1; i < size; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }

        // 计算每个位置右边的最大值
        maxRight[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }

        // 计算接水容积
        int ans = 0;
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }
        return ans;
    }
}

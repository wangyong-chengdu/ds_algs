package cd.wangyong.leetcode.算法.深度遍历搜索;

import cd.wangyong.leetcode.common.TreeNode;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 二叉树中的最大路径和 {

    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root) {
        // dfs递归结束条件
        if (root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        int curMax = Math.max(root.val, Math.max(root.val + left, root.val + right));

        // 当前节点为n,对于当前节点来说，最大值可以为n+left,n+right,n,n+left+right, 四项中的一个
        max = Math.max(max, Math.max(curMax, root.val + left + right));

        // 只能返回maxTemp1,这个结果才能参与dfs
        return curMax;
    }
}

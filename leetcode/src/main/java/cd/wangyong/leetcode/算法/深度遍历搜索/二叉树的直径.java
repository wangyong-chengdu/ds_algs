package cd.wangyong.leetcode.算法.深度遍历搜索;

import cd.wangyong.leetcode.common.TreeNode;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 二叉树的直径 {
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }

    /**
     * depth是树的深度：1个结点树的深度为1
     */
    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }

        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度

        // 最短路径只可能在左右连接中
        max = Math.max(max, L + R); // 计算d_node即L+R+1 并更新ans

        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }
}

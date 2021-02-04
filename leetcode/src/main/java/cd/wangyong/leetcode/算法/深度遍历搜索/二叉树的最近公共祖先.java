package cd.wangyong.leetcode.算法.深度遍历搜索;

import cd.wangyong.leetcode.common.TreeNode;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 二叉树的最近公共祖先 {

    private TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return this.ans;
    }

    /**
     * 节点左右子树是否包含节点
     */
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;

        boolean leftHasNode = dfs(root.left, p, q);
        boolean rightHasNode = dfs(root.right, p, q);

        if ((leftHasNode && rightHasNode) || ((root.val == p.val || root.val == q.val) && (leftHasNode || rightHasNode))) {
            ans = root;
        }

        return leftHasNode || rightHasNode || (root.val == p.val || root.val == q.val);
    }
}

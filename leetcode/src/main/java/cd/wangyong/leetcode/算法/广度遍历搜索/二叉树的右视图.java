package cd.wangyong.leetcode.算法.广度遍历搜索;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cd.wangyong.leetcode.common.TreeNode;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 二叉树的右视图 {
    public List<Integer> rightSideView(TreeNode root) {
        // 边界处理
        if (root == null) return Collections.emptyList();

        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.peek() != null) {
            // 按层次遍历树
            Queue<TreeNode> levelQueue = new LinkedList<>();
            TreeNode rightViewNode = null;
            while (queue.peek() != null) {
                TreeNode node = queue.remove();
                rightViewNode = node; // 不断刷新右视图节点
                if (node.left != null) levelQueue.add(node.left);
                if (node.right != null) levelQueue.add(node.right);
            }
            if (rightViewNode != null) res.add(rightViewNode.val);
            queue = levelQueue;
        }
        return res;
    }
}

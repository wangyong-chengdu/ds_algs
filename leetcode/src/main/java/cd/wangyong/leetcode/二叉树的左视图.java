package cd.wangyong.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author andy
 * @since 2020/12/8
 */
public class 二叉树的左视图 {
    static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }

    public List<Integer> leftSideView(TreeNode root) {
        if (root == null) return Collections.emptyList();

        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode leftViewNode = null;
        while (queue.peek() != null) {
            Queue<TreeNode> nextLevelQueue = new LinkedList<>();
            while (queue.peek() != null) {
                TreeNode node = queue.remove();
                if (leftViewNode == null) leftViewNode = node;
                if (node.left != null) nextLevelQueue.add(node.left);
                if (node.right != null) nextLevelQueue.add(node.right);
            }
            if (leftViewNode != null) {
                res.add(leftViewNode.val);
                leftViewNode = null;
            }
            queue = nextLevelQueue;
        }
        return res;
    }
}

package cd.wangyong.leetcode.算法.广度遍历搜索;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cd.wangyong.leetcode.common.TreeNode;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 二叉树的层序遍历 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.peek() != null) {
            Queue<TreeNode> levelQueue = new LinkedList<>();
            List<Integer> list = new ArrayList<>();
            while (queue.peek() != null) {
                TreeNode node = queue.remove();
                list.add(node.val);
                if (node.left != null) levelQueue.add(node.left);
                if (node.right != null) levelQueue.add(node.right);
            }
            res.add(list);
            queue = levelQueue;
        }
        return res;
    }
}

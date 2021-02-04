package cd.wangyong.leetcode.算法.广度遍历搜索;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import cd.wangyong.leetcode.common.TreeNode;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 二叉树的锯齿形层次遍历 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        // 使用Queue做层次遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean isOdd = true;
        Stack<Integer> stack  = new Stack<>(); // 利用栈先出后进特点做逆向操作。

        while (queue.peek() != null) {

            Queue<TreeNode> levelQueue = new LinkedList<>();
            List<Integer> list = new ArrayList<>();

            // 按层级处理
            while (queue.peek() != null) {
                TreeNode node = queue.remove();
                if (isOdd) list.add(node.val);
                else stack.push(node.val);
                if (node.left != null) levelQueue.add(node.left);
                if (node.right != null) levelQueue.add(node.right);
            }

            //
            while (!stack.empty()) list.add(stack.pop());
            res.add(list);
            queue = levelQueue;
            isOdd = !isOdd;
        }
        return res;
    }
}

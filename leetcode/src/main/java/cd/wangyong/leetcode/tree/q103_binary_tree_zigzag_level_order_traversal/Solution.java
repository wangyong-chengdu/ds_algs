package cd.wangyong.leetcode.tree.q103_binary_tree_zigzag_level_order_traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean isOdd = true;
        Stack<Integer> stack  = new Stack<>();

        while (queue.peek() != null) {
            Queue<TreeNode> levelQueue = new LinkedList<>();
            List<Integer> list = new ArrayList<>();
            while (queue.peek() != null) {
                TreeNode node = queue.remove();
                if (isOdd) list.add(node.val);
                else stack.push(node.val);
                if (node.left != null) levelQueue.add(node.left);
                if (node.right != null) levelQueue.add(node.right);
            }
            while (!stack.empty()) list.add(stack.pop());
            res.add(list);
            queue = levelQueue;
            isOdd = !isOdd;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = buildTree();
        solution.zigzagLevelOrder(root);
    }

    private static TreeNode buildTree() {
        TreeNode root = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        root.left = node9;
        root.right = node20;
        node20.left = node15;
        node20.right = node7;
        return root;
    }

}

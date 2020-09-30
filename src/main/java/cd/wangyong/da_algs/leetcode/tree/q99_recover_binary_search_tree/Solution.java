package cd.wangyong.da_algs.leetcode.tree.q99_recover_binary_search_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyong
 * @since 2020/6/1
 */
public class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public void recoverTree(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        inorderTraversal(root, nums);

        int x = -1, y = -1;
        for (int i = 0;  i < nums.size() - 1; i++) {
            if (nums.get(i + 1) < nums.get(i)) {
                y = nums.get(i + 1);
                if (x != -1) x = nums.get(i);
                else break;
            }
        }
        swap(root, x, y);
    }

    private void inorderTraversal(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inorderTraversal(root.left, nums);
        nums.add(root.val);
        inorderTraversal(root.right, nums);
    }

    private void swap(TreeNode root, int x, int y) {
        if (root == null) return;
        if (root.val == x) root.val = y;
        else if (root.val == y) root.val = x;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = buildTree("[1,3,null,null,2]");
        solution.recoverTree(root);
    }

    private static TreeNode buildTree(String input) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        return root;
    }


}

package cd.wangyong.leetcode.tree.q95_unique_binary_search_trees_ii;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangyong
 * @since 2020/5/31
 */
public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return Collections.emptyList();
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> list = new LinkedList<>();
        if (start > end) {
            list.add(null);
            return list;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTree = generateTrees(start, i - 1);
            List<TreeNode> rightTree = generateTrees(i + 1, end);

            // connect left and right trees to the root i
            for (TreeNode l : leftTree) {
                for (TreeNode r : rightTree) {
                    TreeNode currentTree = new TreeNode(i);
                    currentTree.left = l;
                    currentTree.right = r;
                    list.add(currentTree);
                }
            }
        }
        return list;
    }

    /**
     * 参考https://leetcode.com/problems/unique-binary-search-trees/submissions/
     * 1.g(n) = f(1,n) + f(2,n) + ... + f(n,n)
     * 2.f(i,n) = g(i - 1) * g(n - i)
     * 递推公式：g(n) = g(0) * g(n-1) + g(1) * g(n - 2) + ... + g(n - 1) * g(0)
     * g(0) = g(1) = 1
     */
    public List<TreeNode> generateTrees2(int n) {
        if (n <= 0) return Collections.emptyList();
        if (n == 1) return Collections.singletonList(new TreeNode(1));
        return generateTrees2(1, n);
    }

    /**
     * g(n)
     */
    private List<TreeNode> generateTrees2(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode current = new TreeNode(i);
                    current.left = leftTree;
                    current.right = rightTree;
                    result.add(current);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<TreeNode> treeNodes = solution.generateTrees(3);
        System.out.println(treeNodes);
    }
}

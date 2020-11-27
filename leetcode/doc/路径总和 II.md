# 路径总和 II
- https://leetcode-cn.com/problems/path-sum-ii/
- 本质：二叉树查找
- 思路：使用递归深度优先遍历二叉树(DFS or 回溯，其实是一个概念)；

## 解法
- 思路：树的递归遍历
- 时间复杂度：o(N)

```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return Collections.emptyList();

        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> trace = new LinkedList<>();
        backtrace(trace, root, sum, res);
        return res;
    }

    private void backtrace(LinkedList<Integer> trace, TreeNode node, int sum, List<List<Integer>> res) {
        if (node == null) {
            return;
        }
        
        // 叶子节点等于sum,则说明找到
        if (node.val == sum && node.left == null && node.right == null) {
            trace.addLast(node.val);
            res.add(new LinkedList(trace));
            trace.removeLast();
            return;
        }

        // 遍历左子树
        trace.addLast(node.val);
        backtrace(trace, node.left, sum - node.val, res);
        // trace.removeLast();

        // // 遍历右子树
        // trace.addLast(node.val);
        backtrace(trace, node.right, sum - node.val, res);
        trace.removeLast();
    }
}

```




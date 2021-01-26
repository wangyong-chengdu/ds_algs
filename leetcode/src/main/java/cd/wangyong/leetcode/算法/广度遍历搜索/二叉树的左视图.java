package cd.wangyong.leetcode.算法.广度遍历搜索;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        int index;
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    /**
     * 二叉树的左视图(BFS\层次遍历)
     */
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

//    public List<Integer> leftView(TreeNode root) {
//        if (root == null) return Collections.emptyList();
//        List<Integer> res = new ArrayList<>();
//        visit(root);
//        return res;
//    }

    // https://www.zhihu.com/people/hello_andy
    // https://github.com/wangyong-chengdu
//    private void visit(TreeNode node, List<Integer> res) {
//        if (node == null) return;
//
//        res.add(node.val);
//
//        if (node.left != null) {
//            visit(node.left, res);
//        }
//        else if (node.left == null && node.right != null) {
//            visit(node.right, res);
//        }
//    }


    public static void main(String[] args)  throws IOException {
        String[] inputs = read2Array();
        TreeNode root = buildTree(inputs);
        List<Integer> res = new 二叉树的左视图().leftSideView(root);
        System.out.println(res);
    }

    /**
     * 输入格式： [1,2,3,null,5,null,4]
     */
    private static String[] read2Array() throws IOException {
        // 创建一个BufferedReader对象
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 读取第一行数据
        String line = br.readLine().trim();
        return line.substring(1, line.length() - 1).split(",");
    }

    /**
     * 构建二叉树
     */
    public static TreeNode buildTree(String[] array) throws IOException {
        TreeNode root = buildNode(array, 0);
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (queue.peek() != null) {
            Queue<TreeNode> nextLevelQueue = new LinkedList<>();
            while (queue.peek() != null) {
                TreeNode node = queue.remove();
                TreeNode left = buildNode(array, 2 * node.index + 1);
                TreeNode right = buildNode(array, 2 * node.index + 2);
                node.left = left;
                node.right = right;

                if (left != null) nextLevelQueue.add(left);
                if (right != null) nextLevelQueue.add(right);
            }
            queue = nextLevelQueue;
        }
        return root;
    }

    /**
     * 构建二叉树结点
     */
    private static TreeNode buildNode(String[] array, int index) {
        if (array.length == 0 || index >= array.length || array[index].equals("null")) return null;
        return new TreeNode(index, Integer.parseInt(array[index]));
    }
}

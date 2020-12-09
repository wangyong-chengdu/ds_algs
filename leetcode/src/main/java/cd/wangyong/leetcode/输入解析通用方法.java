package cd.wangyong.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author andy
 * @since 2020/12/9
 */
public class 输入解析通用方法 {

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
     * 输入格式： [1,2,3,null,5,null,4]
     */
    public static String[] read2Array() throws IOException {
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
    public static TreeNode buildNode(String[] array, int index) {
        if (array.length == 0 || index >= array.length || array[index].equals("null")) return null;
        return new TreeNode(index, Integer.parseInt(array[index]));
    }
}

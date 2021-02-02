package cd.wangyong.leetcode.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 通用工具
 * @author andy
 * @since 2021/2/2
 */
public class Util {

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
     * 读取输入，生成List
     */
    public static List<Integer> readString2List() throws IOException {
        // 创建一个BufferedReader对象
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 读取第一行数据
        String line = br.readLine().trim();
        return Arrays.stream(line.split("->")).map(Integer::valueOf).collect(Collectors.toList());
    }

    /**
     * 读取输入，构建链表，返回头结点
     */
    public static ListNode buildList(List<Integer> inputs) {
        if (inputs == null || inputs.size() == 0) return null;
        ListNode head = new ListNode(inputs.get(0));
        ListNode p = head, q;
        for (int i = 1; i < inputs.size(); i++) {
            q = new ListNode(inputs.get(i));
            p.next = q;
            p = q;
        }
        return head;
    }

    /**
     * 打印单链表
     */
    public static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append("->");
            head = head.next;
        }
        if (sb.length() == 0) {
            System.out.println("");
        }
        else {
            System.out.println(sb.substring(0, sb.length() - 2));
        }
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

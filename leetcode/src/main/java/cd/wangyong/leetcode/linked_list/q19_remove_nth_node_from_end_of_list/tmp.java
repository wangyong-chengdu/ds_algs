package cd.wangyong.leetcode.linked_list.q19_remove_nth_node_from_end_of_list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author wangyong
 * @since 2020/7/3
 */
public class tmp {

 public class TreeNode {
     int val;
     TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }

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

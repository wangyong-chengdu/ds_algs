package cd.wangyong.leetcode.common;

/**
 * @author andy
 * @since 2021/2/2
 */
public class TreeNode {

    public int index;
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int index, int val) {
        this.index = index;
        this.val = val;
    }
}

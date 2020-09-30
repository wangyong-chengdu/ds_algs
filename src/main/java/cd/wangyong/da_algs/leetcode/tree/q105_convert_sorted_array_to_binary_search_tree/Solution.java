package cd.wangyong.da_algs.leetcode.tree.q105_convert_sorted_array_to_binary_search_tree;

/**
 * @author wangyong
 * @since 2020/7/3
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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right || left < 0 || right > nums.length) return null;
        int mid = left + (right - left + 1) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBST(nums, left, mid - 1);
        node.right = buildBST(nums, mid + 1, right);
        return node;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode = solution.sortedArrayToBST(new int[]{-10,-3,0,5,9});
        System.out.println(treeNode);
    }

}

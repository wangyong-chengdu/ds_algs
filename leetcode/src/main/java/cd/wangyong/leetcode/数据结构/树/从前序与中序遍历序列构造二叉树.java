package cd.wangyong.leetcode.数据结构.树;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cd.wangyong.leetcode.common.TreeNode;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 从前序与中序遍历序列构造二叉树 {

    /**
     * 递归构造： root, root.left, root.right  return root;
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 边界
        if (preorder.length == 0 || inorder.length == 0) return null;

        // 根据前序遍历顺序找到root、并按照root划分中序遍历左右子树
        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);

        // 构造左右子树Inorder集合
        List<Integer> leftInorderNodes = new ArrayList<>();
        List<Integer> rightInorderNodes = new ArrayList<>();
        boolean isFoundRoot = false;
        for (int ele : inorder) {
            if (ele == rootVal) {
                isFoundRoot = true;
                continue;
            }
            if (!isFoundRoot) {
                leftInorderNodes.add(ele);
            }
            else {
                rightInorderNodes.add(ele);
            }
        }

        // set用于辅助，后续有用
        Set<Integer> left = new HashSet<>(leftInorderNodes);
        Set<Integer> right = new HashSet<>(rightInorderNodes);

        // 构造左右子树 preorder子集
        List<Integer> leftPreorderNodes = new ArrayList<>();
        List<Integer> rightPreorderNodes = new ArrayList<>();
        for (int ele : preorder) {
            if (left.contains(ele)) leftPreorderNodes.add(ele);
            else if (right.contains(ele)) rightPreorderNodes.add(ele);
        }

        // 构造左子树
        root.left = buildTree(leftPreorderNodes.stream().mapToInt(Integer::valueOf).toArray(), leftInorderNodes.stream().mapToInt(Integer::valueOf).toArray());
        // 构造右子树
        root.right = buildTree(rightPreorderNodes.stream().mapToInt(Integer::valueOf).toArray(), rightInorderNodes.stream().mapToInt(Integer::valueOf).toArray());
        return root;
    }
}

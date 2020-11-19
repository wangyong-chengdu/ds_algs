# 字典序的第K小数字
- https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
- 本质：搜索树的查找（其实已经有序了）
- 解题思路：
    - 首先明确什么是字典序.
        - 根据数字的前缀进行排序。
    - 字典序说明已经有序，该问题是一个10叉树问题。（抽象思维）

## 解法1- 查找树
- 思路：10叉树查找，其实就是一个树的先序遍历顺序，有找到第k个有几个问题
    - 怎么确定一个前缀下所有子结点的个数？
        - 答：每层结点的个数 = 下一个前缀 - 当前前缀 （下个前缀 = 当前前缀 +１）
    - 如果第k个树确定就在当前前缀下，怎么继续往下找；
        - 答：如果在当前前缀，则在每一层中找, prefix *= prefix
    - 如果第k个树不在当前前缀，即当前前缀太小了，怎么扩大到合适的前缀；
        - prefix++;
- 时间复杂度  空间复杂度

```java
class Solution {
    /**
      * 按字典序查找第K小的数
      */
    public int findKthNumber(int n, int k) {
        int p = 1; // 从1开始
        k--; // 已经确定了1个字典序，k--
        while (k > 0) {
            // 计算
            int total = countNodes(p, n);
            // 间距太大，在这个前缀下，需要深入一层
            if (total > k) {
                p *= 10;
                k--; //多了一个字典序，k--, 指针下移一层
            }
            // 间距太小，说明不在这个前缀下，p++;
            else {
                p++;
                k -= total;
            }
        }
        return p;
    }

    /**
     * 统计以prefix为前缀下字典序结点的个数
     */
    public int countNodes(long prefix, int n) {
        long nextPrefix = prefix + 1;
        int cnt = 0;
        // 确保当前前缀不越界
        while (prefix <= n) {
            // nextPrefix可能越界，因此需要改下
            cnt += Math.min(n + 1, nextPrefix) - prefix;

            // 父子结点在两层之间跨度为10
            prefix *= 10;
            nextPrefix *= 10;
        }
        return cnt;
    }
}

```








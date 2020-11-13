# 数组中的第K个最大元素
- https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
- 本质：无序数组局部排序（大数据中找到第K大的元素）[局部排序]
- 解题思路：最大堆获取第K大元素
- 应用场景：海量数据求TOP N

## 解法1
- 思路：构造含有K个元素的大顶堆（优先队列），依次遍历，获取第K个元素
- 时间复杂度o(nlogk),空间复杂度：o(k)

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // build Max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
        for (int num : nums) {
            pq.add(num);
            if (pq.size() > k) pq.remove(); // remove最小的
        }
        // 最小的为第K小的
        return pq.remove();
    }
}
```


## 解法2
- 思路：暴力解法，数组全排序
- 时间复杂度o(nlogn)

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
```





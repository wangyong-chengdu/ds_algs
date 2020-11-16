# 剑指 Offer 03. 数组中重复的数字
- https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
- 本质：数组顺序遍历

## 解题
- 思路：利用HashMap做计数
- 时间复杂读：o(n), 空间复杂度o(n)

```java
class Solution {

    public int findRepeatNumber(int[] nums) {
        if (nums.length == 0) return -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int cnt = map.getOrDefault(num, 0);
            if (cnt == 1) return num;
            map.put(num, cnt + 1);
        }
        return -1;
    }
}
```

## 解法2 （原地排序）
- 思路：优化解法1空间复杂度较高的问题，还是要找下规律
    - 如果没有重复数字，那么正常排序后，数字i应该在下标为i的位置，所以思路是重头扫描数组，遇到下标为i的数字如果不是i的话，（假设为m),那么我们就拿与下标m的数字交换。在交换过程中，如果有重复的数字发生，那么终止返回ture
- 时间复杂度o(n)，空间复杂度O(1)

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        int temp;
        for(int i=0;i<nums.length;i++){
            // 通过交换，让第i个位置对应的值nums[i]也是i.即让nums[i] = i，如果不重复，排序后的结果就是i = nums[i]
            while (nums[i] != i) {
                // 若发现在nums[i]这个位置已经有值等于nums[i]，则说明必然重复！
                if(nums[i] == nums[nums[i]]){
                    return nums[i];
                }
                // 交换值
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }
}
```
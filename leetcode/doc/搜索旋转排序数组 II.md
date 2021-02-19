# 搜索旋转排序数组 II
- https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
- 本质：数据二分查找
- 解题思路：二分查找，充分利用有规律部分，遍历时优选规律部分

## 解法：二分查找
- 旋转数组不会出现target锁定在查找区间内，但却跳到另外一个区间情况出现。
- 时间复杂度
- 空间复杂度

```java

class Solution {
    public boolean search(int[] nums, int target) {
        return searchHelper(nums, 0, nums.length - 1, target);
    }
    
    private boolean searchHelper(int[] nums, int left, int right, int target) {
        if (left > right) return false;

        // 处理重复值
        while (left + 1 < right  && nums[left] == nums[left + 1]) left++;
        if (nums[left] == target) return true;
        else if (left == right && nums[left] != target) return false;

        while (right - 1 > left && nums[right] == nums[right - 1]) right--;
        if (nums[right] == target) return true;
        else if (left == right && nums[right] != target) return false;

        // 用于加速，万一瞎猫碰到死耗子呢      
        int mid = left + (right - left) / 2;  
        if (nums[mid] == target) return true;
        
        // mid左边区间有序，则判断目标值是否在区间内；反之，右半边有序
        if (nums[left] < nums[mid]) {
            if (target > nums[left] && target < nums[mid]) return searchHelper(nums, left + 1, mid - 1, target);
            else return searchHelper(nums, mid + 1, right - 1, target);
        }
        else {
            if (target > nums[mid] && target < nums[right]) return searchHelper(nums, mid + 1, right - 1, target);
            else return searchHelper(nums, left + 1, mid - 1, target);
        }
    }
}

```


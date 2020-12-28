# 最大连续1的个数 III
- https://leetcode-cn.com/problems/max-consecutive-ones-iii/
- 本质：数组遍历，滑动窗口
- 解题思路：滑动窗口求最值

## 滑动窗口
- 时间复杂度：o(n)，空间复杂度：o(1)

```java

class Solution {
    public int longestOnes(int[] A, int K) {
        if (A.length == 0) return 0;

        int left = 0, right = 0;
        int oneCnt = 0;

        while (right < A.length) {
            if (A[right] == 1) oneCnt++;

            // 窗口滑动
            if (right - left + 1 > oneCnt + K) {
                if (A[left] == 1) oneCnt--;
                left++;
                right++;
            }
            // 窗口扩展
            else {
                right++;
            }
        }
        return A.length - left;
    }

    
    public int longestSequence(int[] A, int K) {
        if (A.length == 0) return 0;

        int left = 0, right = 0;
        int maxCount = 0;
        int[] cnts = new int[2];

        while (right < A.length) {
            cnts[A[right]]++;
            maxCount = Math.max(maxCount, cnts[A[right]]);

            // 窗口滑动
            if (right - left + 1 > maxCount + K) {
                cnts[A[left]]--;
                left++;
                right++;
            }
            // 窗口扩展
            else {
                right++;
            }
        }
        return A.length - left;
    }
}

```
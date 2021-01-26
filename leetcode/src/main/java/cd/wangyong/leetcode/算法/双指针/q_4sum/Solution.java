package cd.wangyong.leetcode.算法.双指针.q_4sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/4sum/
 *
 * @author wangyong30
 * @since 2020/4/21
 */
public class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();

        int n = nums.length;
        int maxA = n - 4;
        int maxB = n - 3;
        for (int a = 0; a <= maxA; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }

            if (nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3] > target) {
                return res;
            }

            for (int b = a + 1; b <= maxB; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }

                int c = b + 1;
                int d = n - 1;

                if (nums[a] + nums[b] + nums[c] + nums[c + 1] > target) {
                    break;
                }

                if (nums[a] + nums[b] + nums[d] + nums[d - 1] < target) {
                    continue;
                }

                while (c < d) {
                    if (nums[a] + nums[b] + nums[c] + nums[d] == target) {
                        res.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c < d && nums[c] == nums[c + 1]) {
                            c++;
                        }
                        while (c < d && nums[d] == nums[d - 1]) {
                            d--;
                        }
                        c++;
                        d--;
                    }
                    else if (nums[a] + nums[b] + nums[c] + nums[d] > target) {
                        d--;
                    }
                    else {
                        c++;
                    }
                }
            }

        }
        return res;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        if (nums[nums.length - 4] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] - target < 0) {
            return Collections.emptyList();
        }

        for (int i = 0; i <= nums.length - 4; i++) {
            // nums[i] is too large
//            if (4 * nums[i] - target > 0) {
            if ((nums[i] << 2) - target > 0) {
                break;
            }

            // avoid duplicate
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // nums[i] is too small
            if (nums[i]  + nums[nums.length - 3] +  nums[nums.length -2] + nums[nums.length -1] - target < 0) {
                continue;
            }

            for (int j = i + 1; j <=  nums.length - 3; j++) {
                // avoid duplicate
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // nums[j] is too large
                if (nums[i]  + nums[j] + nums[j + 1] + nums[j + 2] - target > 0) {
                    break;
                }

                // nums[j] is too small
                if (nums[i]  + nums[j] + nums[nums.length -2] + nums[nums.length -1] - target < 0) {
                    continue;
                }

                int low = j + 1;
                int high = nums.length - 1;
                while (low < high) {
                    int tmp = nums[i] + nums[j] + nums[low] + nums[high] - target;
                    if (tmp == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        while (low < high && nums[low + 1] == nums[low]) {
                            low++;
                        }
                        while (low < high && nums[high -1] == nums[high]) {
                            high--;
                        }
                        low++;
                        high--;
                    }
                    else if (tmp > 0) {
                        high--;
                    }
                    else {
                        low++;
                    }
                }
            }
        }

        return result;

    }

//    /**
//     * output: [[-4, 0, 1, 2], [-1, -1, 0, 1]]
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        int[] nums = new int[]{0, 0, 0, 0};
//        Solution solution = new Solution();
//        List<List<Integer>> res = solution.fourSum2(nums, 0);
//        System.out.println(res);
//    }

//    public static void main(String[] args) {
//        int[] nums = new int[]{-5,-4,-3,-2,-1,0,0,1,2,3,4,5};
//        Solution solution = new Solution();
//        List<List<Integer>> res = solution.fourSum2(nums, 0);
//        System.out.println(res);
//    }

//    public static void main(String[] args) {
//        int[] nums = new int[]{1,-2,-5,-4,-3,3,3,5};
//        Solution solution = new Solution();
//        List<List<Integer>> res = solution.fourSum2(nums, -11);
//        System.out.println(res);
//    }

    /**
     * output: [[-4, 0, 1, 2], [-1, -1, 0, 1]]
     *
     * @param args
     */
    public static void testCase1(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        Solution solution = new Solution();
        List<List<Integer>> res = solution.fourSum(nums, -1);
        System.out.println(res);
    }

//    /**
//     * output:
//     * [[-5, -4, 4, 5], [-5, -3, 3, 5], [-5, -2, 2, 5], [-5, -2, 3, 4], [-5, -1, 1, 5], [-5, -1, 2, 4], [-5, 0, 0, 5], [-5, 0, 1, 4], [-5, 0, 2, 3],
//     * [-4, -3, 2, 5], [-4, -3, 3, 4], [-4, -2, 1, 5], [-4, -2, 2, 4], [-4, -1, 0, 5], [-4, -1, 1, 4], [-4, -1, 2, 3], [-4, 0, 0, 4], [-4, 0, 1, 3], [-3, -2, 0, 5], [-3, -2, 1, 4], [-3, -2, 2, 3], [-3, -1, 0, 4], [-3, -1, 1, 3], [-3, 0, 0, 3], [-3, 0, 1, 2], [-2, -1, 0, 3], [-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        int[] nums = new int[]{-5, -4, -3, -2, -1, 0, 0, 1, 2, 3, 4, 5};
//        Solution solution = new Solution();
//        List<List<Integer>> res = solution.fourSum2(nums, 0);
//        System.out.println(res);
//    }

    /**
     * output:
     * [[-5, -4, 4, 5], [-5, -3, 3, 5], [-5, -2, 2, 5], [-5, -2, 3, 4], [-5, -1, 1, 5], [-5, -1, 2, 4], [-5, 0, 0, 5], [-5, 0, 1, 4], [-5, 0, 2, 3],
     * [-4, -3, 2, 5], [-4, -3, 3, 4], [-4, -2, 1, 5], [-4, -2, 2, 4], [-4, -1, 0, 5], [-4, -1, 1, 4], [-4, -1, 2, 3], [-4, 0, 0, 4], [-4, 0, 1, 3], [-3, -2, 0, 5], [-3, -2, 1, 4], [-3, -2, 2, 3], [-3, -1, 0, 4], [-3, -1, 1, 3], [-3, 0, 0, 3], [-3, 0, 1, 2], [-2, -1, 0, 3], [-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = new int[]{-5,5,4,-3,0,0,4,-2};
        Solution solution = new Solution();
        List<List<Integer>> res = solution.fourSum2(nums, 4);
        System.out.println(res);
    }
//
//    public static void main(String[] args) {
////        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
////        int[] nums = new int[]{-2, 0, 0, 2, 2};
////        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
//        int[] nums = new int[]{-1,0,1,2,-1,-4};
//        Solution solution = new Solution();
//        List<List<Integer>> res = solution.fourSum(nums, -1);
//        System.out.println(res);
//    }
}

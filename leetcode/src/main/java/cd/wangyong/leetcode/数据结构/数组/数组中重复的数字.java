package cd.wangyong.leetcode.数据结构.数组;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 数组中重复的数字 {

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

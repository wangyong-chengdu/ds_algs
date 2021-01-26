package cd.wangyong.leetcode.算法.双指针.q125_valid_palindrome;

/**
 * @author wangyong
 * @since 2020/6/30
 */
public class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        String t = s.toLowerCase();
        int l = 0, r = t.length() - 1;
        while (l < r) {
            while (l < r && !isAlphaOrDigit(t.charAt(l))) l++;
            while (l < r && !isAlphaOrDigit(t.charAt(r))) r--;
            if (l == r) break;
            if (t.charAt(l) == t.charAt(r)) {
                l++;
                r--;
            }
            else return false;
        }
        return true;
    }

    private boolean isAlphaOrDigit(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(solution.isPalindrome("race a car"));
    }


}

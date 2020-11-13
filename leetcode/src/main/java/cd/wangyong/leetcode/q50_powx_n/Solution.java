package cd.wangyong.leetcode.q50_powx_n;

/**
 * @author wangyong
 * @since 2020/5/26
 */
public class Solution {
    public double getPow(double x, long n) {
        if (n == 0) return 1.0;
        return n % 2 == 0 ? getPow(x * x, n / 2) : x * getPow(x * x, n / 2);
    }

    public double myPow(double x, int n) {
        long m = n;
        return m > 0 ? getPow(x, m) : 1.0 / getPow(x, -m);
    }

    public double myPow1(double x, int n) {
        return Math.pow(x, (double)n);
    }



    private double getPowerByLoop(double x, long n) {
        double result = 1.0, accumulate = x;
        while (n > 0) {
            if (n % 2 == 1) result *= accumulate;
            accumulate *= accumulate;
            n /= 2;
        }
        return result;
    }

    public double myPow3(double x, int n) {
        long m = n;
        return m >= 0 ? getPowerByLoop(x, m) : 1.0 / getPowerByLoop(x, -m);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myPow(2.00000, -2147483648));
        System.out.println(solution.myPow3(2.00000, 4));
    }

}

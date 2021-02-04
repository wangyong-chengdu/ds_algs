package cd.wangyong.leetcode.算法.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author andy
 * @since 2021/1/26
 */
public class 三角形最小路径和 {

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty()) return 0;

        LinkedList<Integer> trace = new LinkedList<>();
        List<Integer> res = new ArrayList<>(1);
        res.add(Integer.MAX_VALUE);
        backtrace(triangle, trace, 0, 0, res);
        return res.get(0);
    }

    public void backtrace(List<List<Integer>> triangle, LinkedList<Integer> trace, int i, int j, List<Integer> res) {
        int n = triangle.size();

        int sum = trace.stream().mapToInt(Integer::intValue).sum();
        if (i >= n || j >= n) {
            if (sum < res.get(0)) {
                res.remove(0);
                res.add(sum);
            }
            return;
        }

        trace.add(triangle.get(i).get(j));// trace add

        backtrace(triangle, trace, i + 1, j, res);
        backtrace(triangle, trace, i + 1, j + 1, res);

        trace.removeLast(); // trace removeLast
    }

    public static void main(String[] args) {
        三角形最小路径和 instance = new 三角形最小路径和();
        System.out.println(instance.minimumTotal(initTriangle()));
    }

    private static List<List<Integer>> initTriangle() {
//        List<List<Integer>> list = new ArrayList<>();
//        list.add(Arrays.asList(2));
//        list.add(Arrays.asList(3, 4));
//        list.add(Arrays.asList(6,5,7));
//        list.add(Arrays.asList(4,1,8,3));

        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(-10));
        return list;
    }


}

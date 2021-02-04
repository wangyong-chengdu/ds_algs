package cd.wangyong.leetcode.数据结构.字典;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 从字符串中移除最频繁出现的字符, 若最频繁字符大于1个，则选择任意一个返回
 * @author andy
 * @since 2021/2/4
 */
public class 从字符串中移除最频繁出现的字符 {

    /**
     * 代码简化
     * @param input
     * @return
     */
    public String removeFrequentChar(String input) {
        if (input == null || input.length() == 0) return input;

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        Character frequentChar = list.get(0).getKey();
        return input.replace(frequentChar + "", "");
    }

    /**
     * 原始解答
     */
    public String removeFrequentChar2(String input) {
        if (input == null || input.length() == 0) return input;

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        Character frequentChar = null;
        int max = -1;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                frequentChar = entry.getKey();
                max = entry.getValue();
            }
        }
        return input.replace(frequentChar + "", "");
    }

    public static void main(String[] args) {
        从字符串中移除最频繁出现的字符 instance = new 从字符串中移除最频繁出现的字符();
        System.out.println(instance.removeFrequentChar("wangyong"));
        System.out.println(instance.removeFrequentChar("hello, chengduooooo"));
    }
}

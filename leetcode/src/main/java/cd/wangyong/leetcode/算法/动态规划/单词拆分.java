package cd.wangyong.leetcode.算法.动态规划;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author andy
 * @since 2021/2/7
 */
public class 单词拆分 {

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1]; // dp表示从0到i(不包含i)是否可通过wordDict进行组合得到。
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = dp[j] && wordDictSet.contains(s.substring(j, i));
                if (dp[i]) break;
//                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
//                    dp[i] = true;
//                    break;
//                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.isEmpty()) return false;

        Map<Integer, Set<String>> dict = new TreeMap<>((a, b) -> Integer.compare(b, a));
        wordDict.forEach(word -> dict.computeIfAbsent(word.length(), k -> new HashSet<>()).add(word));
        return match(s, 0, dict);
    }

    private boolean match(String s, int beginIndex, Map<Integer, Set<String>> dict) {
        int n = s.length();
        if (beginIndex >= n) return false;

        // 字典单词匹配、choice, 可优化
        for (Map.Entry<Integer, Set<String>> entry : dict.entrySet()) {
            int endIndex = beginIndex + entry.getKey();
            if (endIndex > n) continue;
            if (entry.getValue().contains(s.substring(beginIndex, endIndex))) {
                // 尾部正好在字典内，查找成功
                if (endIndex == n) return true;
                // 非尾部，则继续匹配
                boolean isMatched = match(s, endIndex, dict);
                if (isMatched) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        单词拆分 instance = new 单词拆分();
        System.out.println(instance.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(instance.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(instance.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}

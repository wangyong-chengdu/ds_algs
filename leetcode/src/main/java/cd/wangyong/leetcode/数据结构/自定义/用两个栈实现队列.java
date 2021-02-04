package cd.wangyong.leetcode.数据结构.自定义;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author andy
 * @since 2021/2/4
 */
public class 用两个栈实现队列 {

    class CQueue {
        Deque<Integer> stack1;
        Deque<Integer> stack2;

        public CQueue() {
            stack1 = new LinkedList<Integer>();
            stack2 = new LinkedList<Integer>();
        }

        /**
         * 尾部插入
         */
        public void appendTail(int value) {
            stack1.push(value);
        }

        /**
         * 头部删除
         */
        public int deleteHead() {
            // 如果第二个栈为空
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            if (stack2.isEmpty()) {
                return -1;
            } else {
                return stack2.pop();
            }
        }
    }
}

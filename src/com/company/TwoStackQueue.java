/*

两个栈实现一个队列；

 */

package com.company;

import java.util.Stack;

public class TwoStackQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public TwoStackQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    private void offer(int item) {
        stack1.push(item);
    }

    private int poll() throws Exception {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        } else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            if (stack2.isEmpty()) {
                throw new Exception("Queue is empty!");
            }
            return stack2.pop();
        }
    }
}

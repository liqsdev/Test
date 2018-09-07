/*

两个队列实现一个栈；

 */

package com.company;

import java.util.ArrayDeque;
import java.util.Queue;

public class TwoQueueStack {

    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public TwoQueueStack() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }

    private void push(int item) {
        queue1.offer(item);
    }

    private int pop() throws Exception {
        if (queue1.isEmpty()) {
            throw new Exception("Stack is empty!");
        } else {
            while (queue1.size()>1) {
                queue2.offer(queue1.poll());
            }
            int res = queue1.poll();
            while (!queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }
            return res;
        }
    }
}

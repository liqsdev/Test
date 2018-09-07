/*

一个有getMin功能的栈；

 */

package com.company;

import java.util.Stack;

public class MyStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int item) {
        this.stackData.push(item);
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(item);
        } else {
            if (this.getMin() <= item) {
                this.stackMin.push(this.stackMin.peek());
            } else {
                this.stackMin.push(item);
            }
        }
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("Stack Empty!");
        }
        this.stackMin.pop();
        return this.stackData.pop();
    }

    public int getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("Stack Empty!");
        }
        return this.stackMin.peek();
    }
}

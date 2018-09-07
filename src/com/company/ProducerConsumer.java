package com.company;

import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumer {
    Stack<Integer> items = new Stack<Integer>();
    ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
    final static int NO_ITEMS = 15;

    public static void main(String args[]) {
        ProducerConsumer pc = new ProducerConsumer();
        Thread t1 = new Thread(pc.new Producer());
        Consumer consumer  = pc.new Consumer();
        Thread t2 = new Thread(consumer);
        Thread t3 = new Thread(consumer);
        Thread t4 = new Thread(consumer);
        t1.start();
        /*
        try {
            Thread.sleep(100);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        */
        t2.start();
        t3.start();
        t4.start();
        /*
        try {
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }

    class Producer implements Runnable {
        public void produce(int i) {
            try {
                queue.put(i);
                System.out.println("Producing " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            int i = 0;
            // 生产10次
            while (i++ < NO_ITEMS) {
                produce(i);
            }
        }
    }

    class Consumer implements Runnable {


        public void consume() {
            try {
                System.out.println("Consuming "+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (!queue.isEmpty()) {
                consume();
            }
        }
    }
}

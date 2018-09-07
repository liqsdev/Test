package com.company;

public class NewThread  implements Runnable{
    @Override
    public void run() {
        System.out.println("NewThread is running!");
    }
}

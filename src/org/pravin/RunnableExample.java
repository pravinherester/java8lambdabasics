package org.pravin;

public class RunnableExample {
    public static void main(String[] args) {
        Thread mythread = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("Printed inside Runnable");

            }
        });

        Thread myLamdaThread = new Thread(() -> System.out.println("Printed inside Lambda runnable"));
        mythread.run();
        myLamdaThread.run();
    }
}

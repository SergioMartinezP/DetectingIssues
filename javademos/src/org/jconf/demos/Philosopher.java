package org.jconf.demos;

import java.util.concurrent.ThreadLocalRandom;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Philosopher {
    private final String name;
    private final Chopstick left;
    private final Chopstick right;
    private int attempts = 0;
    private final String thinkingString;
    private final String eatingString;
    private final String gotLeftChopstick;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        this.name = name;
        this.left = left;
        this.right = right;
        this.thinkingString = name + " thinking...";
        this.eatingString = name + " eating...";
        this.gotLeftChopstick = name + " got left chopstick...";
    }

    public void sleep(String message) {
        try {
            long time = ThreadLocalRandom.current().nextLong(100, 2000);
            if (message != null) {
                System.out.println(message);
            }
            MILLISECONDS.sleep(time);
        } catch (InterruptedException iex) {
        }
    }

    public void run() {
        while (true) {
            sleep(thinkingString);
            synchronized (left) {
                sleep(gotLeftChopstick);
                synchronized (right) {
                    sleep(eatingString);
                }
            }
        }
    }
}

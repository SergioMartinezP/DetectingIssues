package org.jconf.demos;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Demo04SystemVsUser {
    public static int THREADS_NUMBER = 200;
    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS_NUMBER];
        AtomicLong[] alongs = new AtomicLong[THREADS_NUMBER];
        Random rnd = new Random();
        for (int i = 0; i < THREADS_NUMBER; i++) {
            alongs[i] = new AtomicLong();
            threads[i] = new Thread(new SomeHeavyLoad(alongs, i, true));
        }
        System.out.println("Enter to continue...");
        System.in.read();
        System.out.println("working...");
        for (int i = 0; i < 200; i++) {
            threads[i].start();
        }

    }
}

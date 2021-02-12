package org.jconf.demos;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Demo04SystemVsUser2 {
    public static int THREADS_NUMBER = 200;
    public static boolean DO_IO = true;
    public static void main(String[] args) throws Exception {
        if (args.length == 2){
            THREADS_NUMBER = Integer.parseInt(args[0]);
            DO_IO = Boolean.parseBoolean(args[1]);
        }
        Thread[] threads = new Thread[THREADS_NUMBER];
        AtomicLong[] alongs = new AtomicLong[THREADS_NUMBER];
        Random rnd = new Random();
        for (int i = 0; i < THREADS_NUMBER; i++) {
            alongs[i] = new AtomicLong();
            threads[i] = new Thread(new SomeHeavyLoad(alongs, i, DO_IO));
        }
        System.out.println("Enter to continue...");
        System.in.read();
        System.out.println("working...");
        for (int i = 0; i < THREADS_NUMBER; i++) {
            threads[i].start();
        }

    }
}

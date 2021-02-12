package org.jconf.demos;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo02DeadLock {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Chopstick[] chopsticks = new Chopstick[5];
        Philosopher[] philosophers = new Philosopher[5];
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Chopstick("Chopstick "+(i+1));
        }
        for (int i = 0; i < 5; i++) {
            int right = i == 4? 0: i + 1;
            philosophers[i] = new Philosopher("Philosopher "+(i+1), chopsticks[i], chopsticks[right]);
        }
        System.out.println("Enter to continue...");
        System.in.read();
        Arrays.stream(philosophers).forEach(philosopher -> executor.submit(() ->philosopher.run()));
        System.out.println("working...");
    }
}

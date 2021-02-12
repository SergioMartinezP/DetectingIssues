package org.jconf.demos;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class SomeHeavyLoad implements Runnable {
    private final AtomicLong[] atomics;
    private final int count;
    private final boolean doIO;

    public SomeHeavyLoad(AtomicLong[] atomics, int count, boolean doIO) {
        this.atomics = atomics;
        this.count = count;
        this.doIO = doIO;
    }


    @Override
    public void run() {
        long cont = 0;
        while(true) {
            synchronized (this) {
                long increment = ThreadLocalRandom.current().nextLong(0, 10);
                long current = atomics[count].addAndGet(increment);
                if (doIO && (++cont % 1000 == 0)) {
                    System.out.println(current);
                }
            }
        }
    }
}

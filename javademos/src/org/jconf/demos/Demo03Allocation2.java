package org.jconf.demos;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Demo03Allocation2 {
    public static List<String> nombres;
    public static List<String> apellidos;
    public static HashMap<Long, Person2> mapa = new HashMap();
    private static AtomicLong counter = new AtomicLong();

    public static void main(String[] args) throws Exception {
        nombres = Files.lines(Paths.get("nombres.txt")).collect(toList());
        apellidos = Files.lines(Paths.get("apellidos.txt")).collect(toList());
        for (int i = 0; i < 2_000; i++) {
            String n = nombres.get(ThreadLocalRandom.current().nextInt(nombres.size()));
            String a = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size()));
            long id = counter.incrementAndGet();
            Person2 p = new Person2(n, a, id);
            mapa.putIfAbsent(id, p);
        }
        System.out.println("Enter to continue...");
        System.out.println("Memory:" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        System.in.read();
        System.out.println("working...");
        Collection<Person2> col = mapa.values();
        long ini = System.currentTimeMillis();
        IntStream.range(0, 1_000_000).parallel().forEach(i -> {
            for (Person2 p : col) {
                Person2 p1 = mapa.get(p.getId());
                if (p1 != null) {
                    p1.setAmount(p1.getAmount() + 1);
                }
            }
        });
        System.out.println("Memory:" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        System.out.println("Time: " + (System.currentTimeMillis() - ini));
    }

}

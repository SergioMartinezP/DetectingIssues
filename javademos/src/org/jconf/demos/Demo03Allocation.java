package org.jconf.demos;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Demo03Allocation {
    public static List<String> nombres;
    public static List<String> apellidos;
    public static HashMap<String, Person> mapa = new HashMap();

    public static void main(String[] args) throws Exception {
        nombres = Files.lines(Paths.get("nombres.txt")).collect(toList());
        apellidos = Files.lines(Paths.get("apellidos.txt")).collect(toList());
        for (int i = 0; i < 2_000; i++) {
            String n = nombres.get(ThreadLocalRandom.current().nextInt(nombres.size()));
            String a = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size()));
            Person p = new Person(n, a);
            mapa.putIfAbsent(p.getNombre() + " " + p.getApellido(), p);
        }
        System.out.println("Enter to continue...");
        System.out.println("Memory:" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        System.in.read();
        System.out.println("working...");
        Collection<Person> col = mapa.values();
        long ini = System.currentTimeMillis();
        IntStream.range(0, 1_000_000).parallel().forEach(i -> {
            for (Person p : col) {
                Person p1 = mapa.get(p.getNombre() + " " + p.getApellido());
                if (p1 != null) {
                    p1.setAmount(p1.getAmount() + 1);
                }
            }
        });
        System.out.println("Memory:" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        System.out.println("Time: " + (System.currentTimeMillis() - ini));
    }

}

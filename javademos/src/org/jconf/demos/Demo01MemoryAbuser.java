package org.jconf.demos;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

public class Demo01MemoryAbuser {
    private static HashMap<Person, List<Order>> cache = new HashMap();

    public static List<String> nombres;
    public static List<String> apellidos;
    public static Random rnd = new Random();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        nombres = Files.lines(Paths.get("nombres.txt")).collect(toList());
        apellidos = Files.lines(Paths.get("apellidos.txt")).collect(toList());
        for (int i=0; i<1_000; i++){
            String n = nombres.get(rnd.nextInt(nombres.size()));
            String a = apellidos.get(rnd.nextInt(apellidos.size()));
            Person p = new Person(n, a);
            cache.putIfAbsent(p, new ArrayList<>());
        }
        Collection<Person> col = cache.keySet();
        System.out.println("Enter to continue...");
        System.out.println("Memory:" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        System.in.read();
        System.out.println("working...");
        for (int i=0; i<15_000; i++){
            for (Person p: col){
                List<Order> list = cache.get(p);
                list.add(new Order(p.getNombre()+" "+p.getApellido(),
                        rnd.nextBoolean()?'b':'s', rnd.nextFloat()*20000+35000, rnd.nextFloat()*500));
            }
            Thread.sleep(20);
            for (Person p: col){
                List<Order> list = cache.get(p);
                int size = list.size();
                if (size > 1_000){
                    size = size - 1_000;
                    for (int j = 0; j < size; j++) {
                        list.remove(0);
                    }
                }
            }
        }
        System.out.println("Memory:" + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
    }
}

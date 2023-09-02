package examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, world! ");

        int a = (new Random()).nextInt(2);
        System.out.println(a);
        switch (a) {
            case 0:
                System.out.println("0");
                break;
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

        int b = 0;
        while (b < 10) {
            System.out.println(b);
            b++;
        }

        int c = 0;
        do {
            System.out.println(c);
            c++;
        } while (c<10);

        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);

        l.stream().forEach(System.out::println);


    }
}

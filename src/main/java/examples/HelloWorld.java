package examples;

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
    }
}

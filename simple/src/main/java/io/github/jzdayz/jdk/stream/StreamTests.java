package io.github.jzdayz.jdk.stream;


import java.util.stream.Stream;

public class StreamTests {

    public static void main(String[] args) {


        Stream.of("1", "2", "3", "4")
                .parallel()
                .forEachOrdered(k -> {
                    System.out.println(k);
                    System.out.println(Thread.currentThread().getName());
                });


        System.out.println(1);

    }

}

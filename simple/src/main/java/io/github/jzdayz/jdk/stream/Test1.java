package io.github.jzdayz.jdk.stream;

import org.xnio.streams.Streams;

import java.util.stream.Stream;

public class Test1 {
    public static void main(String[] args) {
        System.out.println(
                Stream.of(1,2,3,4,5,6,7,7,8)
                        .parallel()
                        .reduce(Integer::sum)
                        .orElse(0)
        );
    }
}

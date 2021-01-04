package io.github.jzdayz.jdk.stream;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTests {

    public static void main(String[] args) {

        List<String> a1 = Arrays.asList(
                "1", "2", "3", "4", "5"
        );

        List<String> a2 = Arrays.asList(
                "11", "22", "33", "44", "55"
        );

        List<List<String>> rs = new ArrayList<>();

        rs.add(a1);
        rs.add(a2);

        rs.parallelStream().flatMap(List::stream).collect(Collectors.toList());

    }

}

package io.github.jzdayz.jdk.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Test2 {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class A {
        private String key;
        private int value;
    }


    public static void main(String[] args) {
        ToIntFunction<A> mapper = A::getValue;

        Map<String, IntSummaryStatistics> collect = Arrays.asList(
                new A("key1", 1),
                new A("key1", 2),
                new A("key2", 3),
                new A("key2", 4)
        ).stream()
                .collect(Collectors.groupingBy(
                        A::getKey,
                        Collector.of(
                                IntSummaryStatistics::new,
                                (r, t) -> r.accept(mapper.applyAsInt(t)),
                                (l, r) -> {
                                    l.combine(r);
                                    return l;
                                }, Collector.Characteristics.IDENTITY_FINISH)
                        )
                );

        System.out.println(1);
    }
}

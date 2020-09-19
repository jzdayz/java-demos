package io.github.jzdayz.jdk.concurrent;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {


    public static void main(String[] args) {
        test1();
    }

    private static void test1() {

        new A(1,100).compute();

        CONTAINER.stream().sorted().forEach(k-> System.out.println(k.toString()));
    }

    public static List<Integer> CONTAINER = new CopyOnWriteArrayList<>();

    @AllArgsConstructor
    public static class A extends RecursiveTask<Integer> {
        private int start;
        private int end;
        final static int THRESHOLD = 1;


        public Integer compute() {
            if ((end - start) <= THRESHOLD) {
                System.out.println("查询数据页码:"+end);
                CONTAINER.add(end);
                return null;
            } else {
                int middle = (start + end) / 2;
                A left = new A(start, middle);
                A right = new A(middle, end);
                right.fork();
                right.join();
                left.compute();
                return null;
            }

        }
    }


    public static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        public Integer compute() {
            if (n <= 1)
                return n;
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            return f2.compute() + f1.join();
        }
    }

}

package io.github.jzdayz.jdk.data;

public class DoubleTests {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Double a = 1d;
        Double b = Double.NaN;

        System.out.println(Double.compare(a,b));
    }
}

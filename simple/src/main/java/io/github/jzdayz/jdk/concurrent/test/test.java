package io.github.jzdayz.jdk.concurrent.test;

public class test {
    public static void main(String[] args) {
        A  a = new A();
        B.C c = a.newC();
        Thread t1 = new Thread(()->{
            a.setValue(1);
            System.out.println(c.valueGet());
        },"t1");
        Thread t2 = new Thread(()->{
            System.out.println(a.getValue());
            c.value(3);
            System.out.println(a.getValue());
        },"t2");
        t1.start();
        t2.start();
    }
}

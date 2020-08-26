package io.github.jzdayz.jna;

/**
 * java jna
 */
public class Demo {

    public static void main(String[] args) {
        JNAApiInterface jnaLib = JNAApiInterface.INSTANCE;
        jnaLib.printf("%s a", "DDD");
    }
}
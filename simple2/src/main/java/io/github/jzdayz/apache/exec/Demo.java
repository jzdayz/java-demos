package io.github.jzdayz.apache.exec;

import com.beust.jcommander.JCommander;

public class Demo {
    public static void main(String[] args) {
        JCommanderTest jct = new JCommanderTest();
        String[] argv = { "-log", "2", "-groups", "unit1,unit2,unit3",
                "-debug", "-Doption=value", "a", "b", "c" };
        JCommander.newBuilder()
                .addObject(jct)
                .build()
                .parse(argv);

    }
}

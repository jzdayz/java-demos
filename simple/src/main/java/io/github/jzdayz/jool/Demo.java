package io.github.jzdayz.jool;

import org.jooq.lambda.Unchecked;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class Demo {

    public static void main(String[] args) {
        File dir = new File(System.getProperty("user.dir"));
        Arrays.stream(Objects.requireNonNull(dir.listFiles()))
                .map(Unchecked.function(File::getCanonicalPath))
                .forEach(System.out::println);
    }
}

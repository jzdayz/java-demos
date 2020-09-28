package io.github.jzdayz.jdk.data;

import lombok.extern.slf4j.Slf4j;

import java.util.BitSet;
import java.util.Random;

/**
 *  位图
 */
@Slf4j
public class BitSetTests {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        for (int i = 1; i < 100; i++) {
            log.info(getSex(i));
        }
    }

    static BitSet missSet = new BitSet(010_000_000_000/*八进制，这个是一个亿*/);
    static BitSet sexSet = new BitSet(010_000_000_000);

    static String getSex(int userId) {
        boolean notMiss = missSet.get(userId);
        if (!notMiss) {
            //lazy fetch from database
            String lazySex = new Random().nextBoolean() ? "female" : "male";
            missSet.set(userId, true);
            sexSet.set(userId, "female".equals(lazySex));
        }
        return sexSet.get(userId) ? "female" : "male";
    }
}

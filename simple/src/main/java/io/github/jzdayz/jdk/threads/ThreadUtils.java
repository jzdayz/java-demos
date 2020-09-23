package io.github.jzdayz.jdk.threads;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ThreadUtils {

    public static List<Thread> getThreads() throws Exception {
        Method getThreads = Thread.class.getDeclaredMethod("getThreads");
        getThreads.setAccessible(true);
        Thread[] threads = (Thread[]) getThreads.invoke(null);
        return Arrays.asList(threads);
    }

}

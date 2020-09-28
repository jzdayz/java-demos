package io.github.jzdayz.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

public class CacheBuilderTests {
    public static void main(String[] args) throws Exception{
        test1();
    }

    private static void test1() throws ExecutionException {
        LoadingCache<String, String> lc = CacheBuilder
                .newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return slowMethod(key);
                    }
                });

        String rs = lc.get("1");
        System.out.println(rs);
    }

    static String slowMethod(String key) throws Exception {
        Thread.sleep(1000);
        return key + ".result";
    }
}



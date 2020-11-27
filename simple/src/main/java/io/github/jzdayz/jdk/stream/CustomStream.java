package io.github.jzdayz.jdk.stream;

import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomStream {

    public static final ForkJoinPool JOB_COMMON_POOL = new ForkJoinPool(
            Runtime.getRuntime().availableProcessors() * 3,
            ForkJoinPool.defaultForkJoinWorkerThreadFactory,
            null, true
    );

    public static void main(String[] args) throws Exception {
test1();
        test22();
    }

    private static void test22() {
        final String path = "/Users/huqingfeng/Downloads/9.log";
        try (
                FileInputStream f = new FileInputStream(path);
                ){
            final String s = StreamUtils.copyToString(f, Charset.defaultCharset());
            final String[] split = s.split("\r\n");
            final Map<Integer, Boolean> valid =
                    IntStream.range(1, 1000).mapToObj((x) -> x).collect(Collectors.toMap(k -> k, k -> false));
            Arrays.stream(split)
                    .map(k->k.split("<>")[1])
                    .mapToInt(Integer::parseInt)
                    .forEach(e-> valid.put(e,true));

            valid.forEach((k,v)->{
                if (!v){
                    System.err.printf(" %s 不存在%n",k);
                }

            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void test1() throws Exception {
        final String path = "/Users/huqingfeng/Downloads/9.log";
        JOB_COMMON_POOL.submit(() ->
                IntStream.range(1, 1000)
                        .parallel()
                        .forEach(e -> write(path, String.format("是否考虑是否将快速登录方式登记方介绍来的快放假了电视剧雷锋精神多了副经理圣诞节附件是雷锋精神来得及克里斯多夫接口设计类似的经历 %s 我是<>%s\r\n",Thread.currentThread().getName(), e).getBytes()))
        ).get();
    }

    private static void write(String path, byte[] data) {
        File f = new File(path);
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(f, true)
        ) {
            fileOutputStream.write(data);
            fileOutputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void test() throws InterruptedException, java.util.concurrent.ExecutionException {
        ForkJoinTask<?> submit = JOB_COMMON_POOL.submit(() ->
                Arrays.asList(1, 2, 3, 45, 6, 7, 8, 45646, 5443)
                        .parallelStream()
                        .forEach(e -> System.out.println(Thread.currentThread().getName()))
        );
        submit.get();
        System.out.println(1);
    }
}

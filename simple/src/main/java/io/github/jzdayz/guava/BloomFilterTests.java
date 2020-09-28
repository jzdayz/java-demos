package io.github.jzdayz.guava;

import com.google.common.hash.BloomFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 *  布隆过滤器: 类似hashmap，但是不会存储数据，只是用来判定数据不存在于容器中(跟位图类似，相当于是多个位点的思想)
 *  采用hash算法，定位到具体的index，由于会有hash冲突所以只能判断不存在
 */
@Slf4j
public class BloomFilterTests {
    public static void main(String[] args) {
        test1();
    }

    @SuppressWarnings("UnstableApiUsage")
    private static void test1() {
        BloomFilter<Test> bf = BloomFilter.create((test,into)-> into.putUnencodedChars(test.getName())
                .putInt(test.getAge()),10000000);
        for (int i = 0; i < 1000; i++) {
            bf.put(new Test("Aa",i));
        }

        boolean bb = bf.test(new Test("aA", 1));
        log.info(" bf result : {} ",bb);
    }

    @Data
    @AllArgsConstructor
    private static class Test{
        private String name;
        private int age;
    }
}

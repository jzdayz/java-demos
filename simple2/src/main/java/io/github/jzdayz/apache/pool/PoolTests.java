package io.github.jzdayz.apache.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class PoolTests {
    public static void main(String[] args) throws Exception {
        test1();
    }

    private static void test1() throws Exception {
        GenericObjectPoolConfig<TestBean> c = new GenericObjectPoolConfig<>();
        c.setMaxTotal(1);
        GenericObjectPool<TestBean> pool = new GenericObjectPool<>(new BasePooledObjectFactory<TestBean>() {

            @Override
            public TestBean create() throws Exception {
                return new TestBean("1111");
            }

            @Override
            public PooledObject<TestBean> wrap(TestBean obj) {
                return new DefaultPooledObject<>(obj);
            }

        }, c);
        final TestBean testBean = pool.borrowObject();
        System.out.println(testBean);
//        pool.returnObject(testBean);
        final TestBean testBean1 = pool.borrowObject();
        System.out.println(testBean1);
        System.out.println(pool.getNumActive());
    }
}

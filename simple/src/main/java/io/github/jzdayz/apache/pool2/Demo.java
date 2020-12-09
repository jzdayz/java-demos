package io.github.jzdayz.apache.pool2;


import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

public class Demo {
    public static void main(String[] args) throws Exception{
        test1();
    }

    private static void test1() throws Exception{
        GenericObjectPool.Config c = new GenericObjectPool.Config();
        c.maxActive = 1;
        GenericObjectPool<TestBean> gop = new GenericObjectPool<>(new BasePoolableObjectFactory<TestBean>() {
            @Override
            public TestBean makeObject() throws Exception {
                return new TestBean("aaaa");
            }
        }, c);
        System.out.println(1);
        final TestBean testBean = gop.borrowObject();
        System.out.println(testBean);
        gop.returnObject(testBean);
        final TestBean testBean1 = gop.borrowObject();
        System.out.println(testBean1);
    }
}

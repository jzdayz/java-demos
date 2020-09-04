package io.github.jzdayz.mapstruct;

/**
 *  还是挺好用的
 */
public class Demo {
    public static void main(String[] args) {
        TestBeanB b = new TestBeanB();
        b.setAge(18);
        b.setName("jhon");

        TestBeanD d = TestMapper.INSTANCE.b2d(b);
        System.out.println(d);
    }
}

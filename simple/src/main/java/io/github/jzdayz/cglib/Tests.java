package io.github.jzdayz.cglib;

import net.sf.cglib.core.ClassGenerator;
import net.sf.cglib.core.DefaultGeneratorStrategy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.NoOp;
import net.sf.cglib.transform.TransformingClassGenerator;
import net.sf.cglib.transform.impl.AddPropertyTransformer;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jooq.meta.derby.sys.Sys;
import org.objectweb.asm.Type;
import org.springframework.aop.framework.ProxyFactory;

public class Tests {
    public static void main(String[] args) {
        System.setProperty("cglib.debugLocation","/Users/huqingfeng/Downloads");
        test1();
//        test2();
    }

    private static void test2() {
        ProxyFactory pf = new ProxyFactory();
        pf.setTargetClass(SampleClass.class);
        pf.addAdvice((MethodInterceptor) invocation -> "AAA");
        SampleClass proxy = (SampleClass)pf.getProxy();
        System.out.println(proxy.test(""));
    }

    public static class SampleClass {
        public String test(String input) {
            return "Hello world!";
        }
    }

    private static void test1() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback((FixedValue) () -> "Hello cglib!");
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(""));
    }
}

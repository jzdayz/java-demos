package io.github.jzdayz.spring.framework;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyTests {
    public static void main(String[] args) {
        test1();
    }

    public interface A {
        String show();
    }

    private static void test1() {
        ProxyFactory f = new ProxyFactory();
        f.setTargetClass(A.class);
        f.addAdvice((MethodInterceptor) invocation -> {
            log.info("淦");
            return "proceed";
        });
        A proxy = (A) f.getProxy();
        System.out.println(proxy.show());
    }
}

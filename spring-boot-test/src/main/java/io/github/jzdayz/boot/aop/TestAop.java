package io.github.jzdayz.boot.aop;

import lombok.AllArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@AllArgsConstructor
@Component
public class TestAop implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        SingletonTargetSource targetSource = new SingletonTargetSource(new A());
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTargetSource(targetSource);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice((MethodInterceptor) invocation -> {
            System.out.println(Arrays.toString(invocation.getArguments()));
            return invocation.proceed();
        });
        A proxy = (A) proxyFactory.getProxy();
        System.out.println(proxy.doSomething("111111"));
    }

    public static class A {
        public String doSomething(String show) {
            return show;
        }
    }

}

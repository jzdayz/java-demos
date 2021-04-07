package io.github.jzdayz.boot.ioc2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"/Users/huqingfeng/Downloads");

        SpringApplication springApplication = new SpringApplication(Test2.class);
        springApplication.addInitializers(applicationContext -> {
            ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
            if (beanFactory instanceof DefaultListableBeanFactory){
                ((DefaultListableBeanFactory)beanFactory).setAllowCircularReferences(true);
            }
        });
        ConfigurableApplicationContext context = springApplication.run(args);

        A a = context.getBean(A.class);
        B b = context.getBean(B.class);

        a.test();
        b.test();

    }

    @Service("a")
    public static class A{
        @Autowired
        private B b;

        @Transactional
        public void test(){
            System.out.println(this+"A.test()"+b);
        }
    }
    @Service("b")
    public static class B{
        @Autowired
        private A a;

        @Transactional
        public void test(){
            System.out.println(this+"B.test()"+a);
        }
    }

}

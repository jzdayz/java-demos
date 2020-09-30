package io.github.jzdayz.boot.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class TransactionalTests {
    public static void main(String[] args) {
        try (
                ConfigurableApplicationContext context = SpringApplication.run(TransactionalTests.class)
                ){
            test1(context);
        }
    }

    private static void test1(ConfigurableApplicationContext context) {
        TestA bean = context.getBean(TestA.class);
        bean.mA();
    }

    @Component
    public static class TestA{

        @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
        @Autowired
        private TestA a;

        @Transactional
        public void mA(){
            System.out.println("A");
            a.mB();
        }
        @SuppressWarnings("ImplicitSubclassInspection")
        @Transactional
        private void mB(){
            System.out.println("B");
        }
    }
}

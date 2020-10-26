package io.github.jzdayz.boot.instrument;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableLoadTimeWeaving;


@SpringBootApplication
//@EnableLoadTimeWeaving
public class TestInstrument
//        implements LoadTimeWeaverAware
{
    public static void main(String[] args) {
        try (
                ConfigurableApplicationContext context = SpringApplication.run(TestInstrument.class, args)
        ) {
            TestBean bean = context.getBean(TestBean.class);
            bean.show();
        }
    }

//    @Override
//    public void setLoadTimeWeaver(LoadTimeWeaver loadTimeWeaver) {
//        loadTimeWeaver.addTransformer((loader, className, classBeingRedefined, protectionDomain, cfb) -> {
//            if (!StringUtils.isEmpty(className) && className.endsWith("TestBean")) {
//                System.out.println(className);
//            }
//            return cfb;
//        });
//    }
}

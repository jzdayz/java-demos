package io.github.jzdayz.spring.framework;

import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;

import javax.validation.constraints.NotNull;

public class DataBinderTests {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        A a = new A();
        DataBinder db = new DataBinder(a);
        OptionalValidatorFactoryBean v = new OptionalValidatorFactoryBean();
        v.afterPropertiesSet();
        db.setValidator(v);
        db.validate(B.class);
        BindingResult bindingResult = db.getBindingResult();
        System.out.println(1);
    }

    interface B{

    }

    @Data
    public static class A{
        @NotNull(message = "不可以为空",groups = B.class)
        private String name;
    }
}

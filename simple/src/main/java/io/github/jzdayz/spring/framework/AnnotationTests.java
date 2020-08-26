package io.github.jzdayz.spring.framework;

import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.*;

public class AnnotationTests {

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    @Documented
    @interface A {
        String name() default "";

        @AliasFor("name")
        String value() default "";
    }

    @A("test")
    public static class B {

    }

    public static void main(String[] args) {
        AnnotationAttributes annotationAttributes = AnnotationUtils
                .getAnnotationAttributes(B.class, B.class.getDeclaredAnnotation(A.class));
        System.out.println(annotationAttributes.getString("name"));
    }

}

package io.github.jzdayz.jdk.type;

import org.apache.ibatis.reflection.TypeParameterResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Tests {

    public interface B<T> {
        List<T> list();
    }

    public interface C extends B<String> {

    }

    public static void main(String[] args) throws Exception {
        final Method t1 = B.class.getDeclaredMethod("list");
        t1.setAccessible(true);
        // String.class
        Type type = TypeParameterResolver.resolveReturnType(t1, C.class);
        System.out.println(type);


        List<String> list = new ArrayList<>();
    }

}

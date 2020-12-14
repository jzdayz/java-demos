package io.github.jzdayz.tests;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Simple3 {

    public static void main(String[] args) {
        List<String> rs  = new ArrayList<>();
        ReflectionUtils.doWithFields(UfGzlXmyht.class, (field -> {
            String name = field.getName();
            name = name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1);
            String setMethod = "set" + name;
            if (!text.contains(setMethod)){
                rs.add(setMethod);
            }
        }), field -> {
            int modifiers = field.getModifiers();
            return !Modifier.isStatic(modifiers);
        });
        System.out.println(rs);
    }

    private final static String text = "";


}

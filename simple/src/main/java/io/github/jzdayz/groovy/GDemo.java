package io.github.jzdayz.groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class GDemo {

    public static void main(String[] args) {

        String groovyContent = "package io.github.jzdayz.groovy\n" +
                "\n" +
                "class Demo{\n" +
                "    static void pi(def str){\n" +
                "        println(str)\n" +
                "    }\n" +
                "}";

        invoke(groovyContent);
        groovyContent = "package io.github.jzdayz.groovy\n" +
                "\n" +
                "class Demo{\n" +
                "    static void pi(def str){\n" +
                "        println(\"111111111\")\n" +
                "    }\n" +
                "}";

        invoke(groovyContent);


    }

    private static void invoke(String groovyContent) {
        ClassLoader cl = GDemo.class.getClassLoader();
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader(cl);

        Class groovyClass = groovyClassLoader.parseClass(groovyContent);
        try {
            GroovyObject object = (GroovyObject) groovyClass.newInstance();
            object.invokeMethod("pi", "hello groovy");
            System.out.println("invoke success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

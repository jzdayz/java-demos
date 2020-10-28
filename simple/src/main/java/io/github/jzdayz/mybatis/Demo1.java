package io.github.jzdayz.mybatis;

import org.apache.ibatis.parsing.PropertyParser;

import java.util.Properties;

public class Demo1 {
    public static void main(String[] args) {
        Properties a = new Properties();
        a.put("aa",999);
        System.out.println(PropertyParser.parse("select * from a where id = ${aa}",a));
    }
}

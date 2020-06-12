package io.github.jzdayz.local.files;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String s = "WXZJ2-陈鹏.jpg";
        Pattern compile =
                Pattern.compile("([a-zA-Z]|[0-9])*");
        Matcher matcher = compile.matcher(s);
//        while (matcher.find()){
//            System.out.println(matcher.group());
//        }


         compile =
                Pattern.compile("[\\u4e00-\\u9fa5]*");
         matcher = compile.matcher(s);
        while (matcher.find()){
            System.out.println(matcher.group()+"11111");
        }
    }
}

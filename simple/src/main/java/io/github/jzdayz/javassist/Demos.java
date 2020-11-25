package io.github.jzdayz.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class Demos {
    public static void main(String[] args) throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("io.github.jzdayz.javassist.Demos");
        CtClass string = pool.get("java.lang.String");
        CtMethod show = cc.getDeclaredMethod("show",new CtClass[]{string});
        show.setName("show11111111");

        show.instrument(
                new ExprEditor() {
                    public void edit(MethodCall m)
                            throws CannotCompileException
                    {
                            m.replace("{ $_ = $proceed($$); }");
                    }
                });
        cc.writeFile("/Users/huqingfeng/Downloads");
    }

    public void show(String a){
        System.out.println("aaaa");
    }
}

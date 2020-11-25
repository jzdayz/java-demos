package io.github.jzdayz.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class Demo1 {
    public static void main(String[] args) throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("io.github.jzdayz.javassist.TestBean");
        CtMethod show = cc.getDeclaredMethod("show");
        show.insertBefore("System.out.println(1);");
        MethodInfo methodInfo = show.getMethodInfo();
        LocalVariableAttribute attribute =
                (LocalVariableAttribute) methodInfo.getCodeAttribute().getAttribute(LocalVariableAttribute.tag);
        System.out.println(attribute.variableName(1));
    }
}

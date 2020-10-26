package io.github.jzdayz.instrument;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.Instrumentation;
import java.util.Objects;

public class InstrumentMain {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer((loader, className, classBeingRedefined, protectionDomain, bytes) -> {
            if (Objects.equals("io/github/jzdayz/boot/instrument/TestBean", className)) {
                try {
                    ClassPool classPool = ClassPool.getDefault();
                    CtClass ctClass = classPool.get("io.github.jzdayz.boot.instrument.TestBean");
                    CtMethod ctMethod = ctClass.getDeclaredMethod("show");
                    ctMethod.insertBefore("System.out.println(\"111111!!!\");");
                    return ctClass.toBytecode();
                } catch (Exception e) {
                    System.out.println("错误");
                }
            }
            return bytes;
        });
    }

}

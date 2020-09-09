package io.github.jzdayz.jdk.concurrent.test;

public abstract class B {

    private int value = 0;

    public void setValue(int v){
        this.value = v;
    }

    public int getValue(){
        return this.value;
    }

    public class C{

        public void value(int a){
            setValue(a);
        }

        public int valueGet(){
            return getValue();
        }

    }
}

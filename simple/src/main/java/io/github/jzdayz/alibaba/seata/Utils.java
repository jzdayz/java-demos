package io.github.jzdayz.alibaba.seata;

import java.util.function.Consumer;

public abstract class Utils {

    interface SupplierEx<T> {
        T get() throws Exception;
    }

    interface ConsumerEx<T> {
        void accept(T t) throws Exception;
    }

    public static <C extends AutoCloseable> void closeDoing(SupplierEx<C> closeable, ConsumerEx<C> cs) {
        try (C c = closeable.get()) {
            cs.accept(c);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

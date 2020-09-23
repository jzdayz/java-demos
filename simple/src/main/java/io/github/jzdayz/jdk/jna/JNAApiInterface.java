package io.github.jzdayz.jdk.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public interface JNAApiInterface extends Library {

    JNAApiInterface INSTANCE = Native
            .load((Platform.isWindows() ? "msvcrt" : "c"), JNAApiInterface.class);

    void printf(String format, Object... args);

    int sprintf(byte[] buffer, String format, Object... args);

    int scanf(String format, Object... args);
}
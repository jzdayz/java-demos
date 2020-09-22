package io.github.jzdayz.hutool;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DESede;

import java.nio.charset.StandardCharsets;

public class Demo {

    public static void main(String[] args) {
        DESede desede = SecureUtil.desede("8989XX89988989898989xXXX".getBytes(StandardCharsets.UTF_8));
        final String m = "PFl7yhUlgkt415DcZ44R8pkCP+Fm22KulVZoJDLhZH47FNnu1gi8rVLxu+RMsEtE";
        String s = desede.decryptStr(m);
        System.out.println("result:" + s);
    }

}

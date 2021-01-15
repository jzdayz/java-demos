package io.github.jzdayz.hutool;

import cn.hutool.system.oshi.OshiUtil;
import oshi.hardware.Sensors;
import oshi.software.os.OperatingSystem;

public class Demo2 {
    public static void main(String[] args) {
        OperatingSystem os = OshiUtil.getOs();
        System.out.println();
        System.out.println(0.85-0.84);
    }
}

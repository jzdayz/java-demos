package io.github.jzdayz.jdk.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class NioClientDemo {
    public static void main(String[] args) {
        NioClient nioClient = new NioClient("localhost", 40404, (sc) -> {
            ByteBuffer allocate = ByteBuffer.allocate(10);
            int read = 0;
            try {
                read = sc.read(allocate);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (read > 0) {
                allocate.flip();
                byte[] data = new byte[allocate.remaining()];
                allocate.get(data);
                System.out.println("收到数据：" + new String(data, StandardCharsets.UTF_8));
            } else if (read < 0) {
                // 对方关闭了链接
                try {
                    sc.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("输入信息：");
            String next = sc.next();
            if ("q".equals(next)) {
                return;
            }
            nioClient.write(ByteBuffer.wrap(next.getBytes(StandardCharsets.UTF_8)));
        }

    }
}

package io.github.jzdayz.jdk.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NioServerDemo {
    public static void main(String[] args) {
        NioServer ni = new NioServer("0.0.0.0", 40404, (sc) -> {
            ByteBuffer bb = ByteBuffer.allocate(1024);
            try {
                final int read = sc.read(bb);
                if (read > 0) {
                    bb.flip();
                    byte[] content = new byte[bb.remaining()];
                    bb.get(content);
                    final String str = new String(content, StandardCharsets.UTF_8);
                    System.out.println("收到:" + str);
                    write(sc, content);
                }
                if (read < 0) {
                    System.out.println("链接关闭");
                    sc.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        ni.start();
    }

    private static void write(SocketChannel sc, byte[] content) throws IOException {
        // 写回去
        sc.write(ByteBuffer.wrap(content));
    }
}

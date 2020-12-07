package io.github.jzdayz.jdk.nio;

import java.nio.channels.SocketChannel;

public interface SocketChannelHandler {

    void handler(SocketChannel sc);

}

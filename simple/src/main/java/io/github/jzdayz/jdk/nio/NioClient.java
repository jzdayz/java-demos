package io.github.jzdayz.jdk.nio;

import oracle.ucp.util.logging.UCPFormatter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient {
    private Selector selector;
    private SocketChannelHandler sch;
    private SocketChannel sc;

    public NioClient(String host, int port, SocketChannelHandler sch) {
        init(host, port, sch);
        Thread thread = new Thread(this::start);
        thread.setName("NIO-CLIENT");
        thread.setDaemon(true);
        thread.start();
    }

    private void init(String host, int port, SocketChannelHandler sch) {
        try {
            selector = Selector.open();
            this.sch = sch;
            this.sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress(host, port));
            sc.register(selector, SelectionKey.OP_READ);
            while (!sc.finishConnect()) {/*ignore*/}
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(ByteBuffer bb) {
        try {
            sc.write(bb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void start() {
        while (true) {
            try {
                if (!(sc.isOpen() && 0 != this.selector.select())) {
                    Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        if (key.isReadable()){
                            SocketChannel channel = (SocketChannel) key.channel();
                            this.sch.handler(channel);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package io.github.jzdayz.jdk.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

@Slf4j
public class NioServer {

    private Selector selector;

    private ServerSocketChannel ssc;

    private SocketChannelHandler sch;

    public NioServer(String host, int port, SocketChannelHandler sch) {
        init(host, port, sch);
    }

    private void init(String host, int port, SocketChannelHandler sch) {
        try {
            ssc = ServerSocketChannel.open();
            selector = Selector.open();
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(host, port));
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            this.sch = sch;
            log.info("ServerBind => [{}:{}]", host, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        start0();
    }

    private void start0() {
        while (true) {
            try {
                if (!(selector.isOpen() && selector.select() != 0)) {
                    final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        final SelectionKey sk = iterator.next();
                        if (sk.isAcceptable()) {
                            SocketChannel accept = this.ssc.accept();
                            log.info("socket accept => {}", accept.getRemoteAddress());
                            accept.configureBlocking(false);
                            accept.register(this.selector, SelectionKey.OP_READ);
                        }
                        if (sk.isReadable()) {
                            final SocketChannel socketChannel = (SocketChannel) sk.channel();
                            this.sch.handler(socketChannel);
                        }
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

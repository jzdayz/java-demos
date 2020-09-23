package io.github.jzdayz.jdk.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel sc =
                ServerSocketChannel.open();
        sc.bind(new InetSocketAddress("localhost", 8081));
        sc.configureBlocking(false);
        Selector selector = Selector.open();
        sc.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("start");
        while (true) {
            int select = selector.select();
            if (select == 0) {
                continue;
            }
            Iterator<SelectionKey> selectionKeys =
                    selector.selectedKeys().iterator();

            while (selectionKeys.hasNext()) {
                SelectionKey selectionKey = selectionKeys.next();
                handler(selectionKey, selector);
                selectionKeys.remove();
            }
        }
    }

    public static void handler(SelectionKey selectionKey, Selector selector) throws Exception {
        if (selectionKey.isAcceptable()) {
            System.out.println("建立连接");
            ServerSocketChannel channel =
                    (ServerSocketChannel) selectionKey.channel();
            SocketChannel accept =
                    channel.accept();
            accept.configureBlocking(false);
            accept.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isConnectable()) {

        } else if (selectionKey.isReadable()) {
            System.out.println("读取数据");
            SocketChannel channel =
                    (SocketChannel) selectionKey.channel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            channel.read(buf);
            System.err.println("res -> " + new String(buf.array()));
            TimeUnit.SECONDS.sleep(1);
        } else {

        }
    }
}

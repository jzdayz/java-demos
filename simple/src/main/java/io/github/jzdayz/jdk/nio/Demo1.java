package io.github.jzdayz.jdk.nio;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.log.StaticLog;
import cn.hutool.socket.nio.NioUtil;
import cn.hutool.socket.nio.Operation;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class Demo1 {
    public static void main(String[] args) throws Exception {
        server();
    }

    private static void server() throws Exception {
        final ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress("0.0.0.0", 30303));
        final Selector st = Selector.open();
        ssc.register(st, SelectionKey.OP_ACCEPT);
        while (st.isOpen() && 0 != st.select()) {
            final Iterator<SelectionKey> keyIter = st.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                handle(keyIter.next(), ssc);
                keyIter.remove();
            }
        }
    }

    private static void handle(SelectionKey key, ServerSocketChannel ssc) {
        if (key.isAcceptable()) {
            SocketChannel socketChannel;
            try {
                // 获取连接到此服务器的客户端通道
                socketChannel = ssc.accept();
                StaticLog.debug("Server [{}] accepted.", socketChannel.getRemoteAddress());
            } catch (IOException e) {
                throw new IORuntimeException(e);
            }

            // SocketChannel通道的可读事件注册到Selector中
            NioUtil.registerChannel(key.selector(), socketChannel, Operation.READ);
        }
        // 读事件就绪
        if (key.isReadable()) {
            final SocketChannel socketChannel = (SocketChannel) key.channel();
            try {
                System.out.println("可读");
                // 如果发生可读事件，但是一直不从sc中读取，则会一直触发可读事件
                handleServer(socketChannel);
            } catch (Exception e) {
                IoUtil.close(socketChannel);
                StaticLog.error(e);
            }
        }

    }

    private static void handleServer(SocketChannel socketChannel) throws Exception {
        final ByteBuffer allocate = ByteBuffer.allocate(1024);
        final int read = socketChannel.read(allocate);
        if (read > 0) {
            // 刚刚写入了数据，现在需要将该buff设置为读模式，也就是从头开始读
            allocate.flip();
            byte[] content = new byte[allocate.remaining()];
            allocate.get(content);
            System.out.println("收到:" + new String(content));
        }

        String ping = "PING";
        ByteBuffer bb = ByteBuffer.wrap(ping.getBytes(StandardCharsets.UTF_8));
        socketChannel.write(bb);
    }
}

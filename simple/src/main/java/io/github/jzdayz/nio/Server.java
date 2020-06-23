package io.github.jzdayz.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class Server {

  public static void main(String[] args) throws Exception {
    ServerSocketChannel sc =
        ServerSocketChannel.open();
    sc.bind(new InetSocketAddress("localhost", 8081));
    sc.configureBlocking(false);
    Selector selector = Selector.open();
    SelectionKey register =
        sc.register(selector, SelectionKey.OP_ACCEPT);
    register.attach("11");

    System.out.println("start");
    while (true) {
      int select = selector.select();
      if (select == 0) {
        continue;
      }
      Set<SelectionKey> selectionKeys =
          selector.selectedKeys();
      for (SelectionKey selectionKey : selectionKeys) {
        System.err.println(selectionKey.attachment());
        handler(selectionKey, selector);
      }
    }
  }

  public static void handler(SelectionKey selectionKey, Selector selector) throws Exception {
    if (selectionKey.isAcceptable()) {
      System.out.println("有连接");
      ServerSocketChannel channel =
          (ServerSocketChannel) selectionKey.channel();
      SocketChannel accept =
          channel.accept();
    } else if (selectionKey.isConnectable()) {
      System.out.println("connectable");
    } else if (selectionKey.isReadable()) {

    } else {
      SocketChannel channel =
          (SocketChannel) selectionKey.channel();
      channel.write(ByteBuffer.wrap("PONG".getBytes()));
      channel.close();
    }
  }
}

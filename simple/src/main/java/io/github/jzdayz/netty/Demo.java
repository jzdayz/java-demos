package io.github.jzdayz.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import java.nio.charset.StandardCharsets;

public class Demo {

  public static void main(String[] args) {
    ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
    buffer.writeBytes("你好".getBytes(StandardCharsets.UTF_8));
    System.out.println(buffer.refCnt());
    ByteBuf sub = buffer.retainedSlice(0, 1);
    System.out.println(sub.refCnt());
    sub.release();
    System.out.println(sub.refCnt());
    System.out.println(buffer.refCnt());
  }

}

package io.github.jzdayz.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Main {

  private static int port = 8000;

  private static String address = "225.0.0.2";

  static {
    System.setProperty("java.net.preferIPv4Stack", "true");
  }

  public static void main(String[] args) throws Exception {

    try {

      InetAddress group = InetAddress.getByName(address);

      MulticastSocket mss = null;

      mss = new MulticastSocket(port);

      mss.joinGroup(group);

      while (true) {

        String message = "Hello from node1";

        byte[] buffer = message.getBytes();

        DatagramPacket dp = new DatagramPacket(buffer, buffer.length,

            group, port);

        mss.send(dp);

        Thread.sleep(1000);

      }

    } catch (IOException e) {

      e.printStackTrace();

    }

  }

}

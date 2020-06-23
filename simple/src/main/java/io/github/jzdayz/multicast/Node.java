package io.github.jzdayz.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Node {

  private static int port = 8000;

  private static String address = "225.0.0.2";

  static{
    System.setProperty("java.net.preferIPv4Stack","true");
  }

  public static void main(String[] args) throws Exception {

    InetAddress group = InetAddress.getByName(address);

    MulticastSocket msr = null;

    try {

      msr = new MulticastSocket(port);

      msr.joinGroup(group);

      byte[] buffer = new byte[1024];

      while (true) {

        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);

        msr.receive(dp);

        String s = new String(dp.getData(), 0, dp.getLength());

        System.out.println("receive from node1:" + s);

      }

    } catch (IOException e) {

      e.printStackTrace();

    }

  }

}
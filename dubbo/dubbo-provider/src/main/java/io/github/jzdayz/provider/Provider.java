package io.github.jzdayz.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {

  public static void main(String[] args) throws Exception {
    System.setProperty("java.net.preferIPv4Stack", "true");
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        new String[]{"META-INF/spring/provider.xml"});
    context.start();
    System.out.println("Provider started.");
    System.in.read(); // press any key to exit
  }
}
package io.github.jzdayz.spring.framework;

import org.springframework.web.util.DefaultUriBuilderFactory;

import java.net.URI;

import static java.util.Collections.singletonMap;

public class UriTests {
    public static void main(String[] args) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);
        URI uri = factory.expand("https://baidu.com/v42/customers/{id}", singletonMap("id", 123L));
        System.out.println(uri);
    }
}

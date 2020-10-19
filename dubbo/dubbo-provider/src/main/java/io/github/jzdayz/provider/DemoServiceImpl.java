package io.github.jzdayz.provider;


import io.github.jzdayz.simple.api.DemoService;

public class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        return "Hello " + name;
    }
}
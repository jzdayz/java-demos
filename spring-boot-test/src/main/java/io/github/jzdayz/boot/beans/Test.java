package io.github.jzdayz.boot.beans;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@AllArgsConstructor
public class Test {


    private ObjectProvider<List<Demo.A>> aa;

    @PostConstruct
    public void init(){
        System.out.println(aa.getIfAvailable());
    }

}

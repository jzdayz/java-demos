package io.github.jzdayz.boot.resource;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
@AllArgsConstructor
public class ResourceTests implements ApplicationRunner {

    private ResourceLoader resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        test1();
    }

    private void test1() throws Exception{
        URL url = new URL("https://www.baidu.com/");
        try (
                InputStream inputStream = url.openStream()
                ){
            System.out.println(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
        }
    }
}

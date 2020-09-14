package io.github.jzdayz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//@EnableWebSecurity
@Controller
@RequestMapping("/testApi")
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/text")
    public ResponseEntity<String> test(){
        HttpHeaders h = new HttpHeaders();
        h.set(HttpHeaders.CONTENT_TYPE,"text/plain");
        ResponseEntity<String> r = new ResponseEntity<>("111",h, HttpStatus.OK);
        return r;
    }

    @ResponseBody
    @GetMapping("/json")
    public Object test1(){
        Map<String,Object> a = new HashMap<>();
        a.put("a","b");
        return a;
    }
}

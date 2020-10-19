package io.github.jzdayz.test;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping
@Controller
public class ApiController {

    @RequestMapping("test")
    @ResponseStatus(HttpStatus.FOUND)
    public void test1(HttpServletRequest request, HttpServletResponse response){
        response.setHeader(HttpHeaders.LOCATION,"https://baidu.com");

    }

    @GetMapping("login")
    @ResponseBody
    public Object test2(HttpServletRequest request){
        return "OK";
    }

}

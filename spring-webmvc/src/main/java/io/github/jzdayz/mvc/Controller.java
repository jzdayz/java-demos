package io.github.jzdayz.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

  @RequestMapping("/**")
  public String test(){
    return "myView";
  }

}

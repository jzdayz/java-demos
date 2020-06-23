package io.github.jzdayz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// This will allow us to reinitialize this controller to get any new config
// values when the /refresh endpoint is POSTed to.
@RefreshScope
public class AppController {

  @Value("${info.foo}")
  private String fooProperty;

  @Autowired
  private ContextRefresher re;

  @RequestMapping("/")
  public String hello() {
    return "Using [" + fooProperty + "] from config server";
  }

//    @RequestMapping("/refresh")
//    public String hello1() {
//        re.refresh();
//        return "1";
//    }
}

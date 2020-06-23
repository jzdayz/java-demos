package io.github.jzdayz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("测试类")
@RestController
public class Controller {

  @ApiOperation("这个是个简答的测试")
  @GetMapping
  public Object test(String name) {
    return name;
  }

}

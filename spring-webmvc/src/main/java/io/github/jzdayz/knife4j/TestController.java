package io.github.jzdayz.knife4j;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping("4jtest")
public class TestController {

    public interface TestApi{}
    public interface TestApi2{}

    @Data
    public static class Test{
        @NotNull(groups = TestApi.class)
        private String name;
        @NotNull(groups = TestApi2.class)
        private Integer age;
    }

    @ApiOperation("测试1")
    @PostMapping("test1")
    @ApiParam(name = "name",required = false)
    public Object test(@RequestBody @Validated(TestApi.class) Test test){
        return test;
    }

    @ApiOperation("测试2")
    @PostMapping("test2")
    public Object test2(@RequestBody @Validated(TestApi2.class) Test test){
        return test;
    }

}

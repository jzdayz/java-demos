package io.github.jzdayz.mybatis;

import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface TestMapper {

    List<Test> all();



    @MapKey("id")
    Map<String,Map<String,Object>> map(Integer id);

}

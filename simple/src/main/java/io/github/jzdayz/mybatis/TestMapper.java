package io.github.jzdayz.mybatis;

import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TestMapper {

    List<Test> all();
    Optional<Test> one();

    @MapKey("id")
    Map<String,Map<String,Object>> map(Integer id);

    boolean insert(Test test);

    boolean insertBase(Test test);

}

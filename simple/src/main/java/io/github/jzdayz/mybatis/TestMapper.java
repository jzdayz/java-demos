package io.github.jzdayz.mybatis;

import io.github.jzdayz.javassist.TestBean;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.session.ResultHandler;

import java.util.LinkedHashMap;
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

    void mapAll(ResultHandler<Test> rh);

}

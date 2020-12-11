package io.github.jzdayz.alibaba.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import lombok.Data;

public class Demo {
    public static void main(String[] args) {
        TestBean t = new TestBean();
        t.setName("111");
        System.out.println(JSON.toJSONString(t, (NameFilter) (object, name, value) -> {
            if (name.equals("name")){
                return "newName";
            }
            return name;
        }));

    }

    @Data
    private static class TestBean{
        private String name;
    }
}

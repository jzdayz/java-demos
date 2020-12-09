package io.github.jzdayz.protostuff;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer age;

}

package io.github.jzdayz.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TestMapper {
    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

    TestBeanA b2a(TestBeanB source);

    TestBeanC b2c(TestBeanB source);

    @Mapping(target = "simpleName", source = "name")
    @Mapping(target = "name", source = "age")
    TestBeanD b2d(TestBeanB source);

}

package io.github.jzdayz.config;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SimpleDateAnnotationFormatterFactory implements AnnotationFormatterFactory<SimpleDate> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        return new HashSet<>(Collections.singletonList(Date.class));
    }

    @Override
    public Printer<?> getPrinter(SimpleDate annotation, Class<?> fieldType) {
        return (Printer<Object>) (date, locale) -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.format(date);
        };
    }

    @Override
    public Parser<?> getParser(SimpleDate annotation, Class<?> fieldType) {
        return (Parser<Object>) (textDate, locale) -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.parse(textDate);
        };
    }
}

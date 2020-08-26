package io.github.jzdayz.apache.configuration2;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

@Slf4j
public class Demo {

    public static void main(String[] args) {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(
                    // load from classpath
                    "apache/configuration2/test.properties"
            );
            // access configuration properties
            log.info("a: {} , b: {} , c: {}",
                    config.getString("a"), config.getString("b"), config.getString("c"));
        } catch (ConfigurationException cex) {
            // Something went wrong
        }
    }
}

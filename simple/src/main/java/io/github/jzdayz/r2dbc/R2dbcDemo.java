package io.github.jzdayz.r2dbc;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class R2dbcDemo {
    public static void main(String[] args) throws InterruptedException {
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "mysql")
                .option(ConnectionFactoryOptions.HOST, "localhost")
                .option(ConnectionFactoryOptions.PORT, 3306)
                .option(ConnectionFactoryOptions.DATABASE, "test")
                .option(ConnectionFactoryOptions.USER, "root")
                .option(ConnectionFactoryOptions.PASSWORD, "123123123")
                .build();

        ConnectionFactory connectionFactory = ConnectionFactories
                .get(options);


        final List<String> name = Mono.from(connectionFactory.create())
                .flatMapMany(connection -> connection
                        .createStatement("SELECT * FROM table1")
                        .execute()
                ).flatMap(result -> result.map((row, rowMetadata) -> row.get("name", String.class)))
                .doOnNext((x) -> System.out.println(Thread.currentThread().getName() + x))
                .buffer()
                .log()
                .blockLast();

        System.out.println(name);
    }
}

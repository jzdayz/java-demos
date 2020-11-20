package io.github.jzdayz.alibaba.seata;

import com.zaxxer.hikari.HikariDataSource;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.rm.RMClient;
import io.seata.rm.datasource.ConnectionProxy;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.tm.TMClient;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;
import io.undertow.Undertow;
import io.undertow.util.HeaderMap;
import io.undertow.util.HeaderValues;
import io.undertow.util.Headers;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class Demo2 {


    public static void main(String[] args) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/test2?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("123123123");
        ds.setAutoCommit(false);
        DataSourceProxy dataSourceProxy = new DataSourceProxy(ds);

        TMClient.init("test1", "my_test_tx_group");
        RMClient.init("test1", "my_test_tx_group");
        Undertow server = Undertow.builder()
                .addHttpListener(9002, "localhost")
                .setHandler(exchange -> {
                    String xid = exchange.getRequestHeaders().get("xid").getFirst();
                    handler(xid, dataSourceProxy);
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send("Hello World");
                }).build();
        server.start();
    }

    private static void handler(String xid, DataSourceProxy ds) {
        RootContext.bind(xid);
        Utils.closeDoing(ds::getConnection, (cnn) -> {
            Utils.closeDoing(cnn::createStatement, (statement) ->
                    statement.execute("insert test values (null,'DEMO2')")
            );
            cnn.commit();
        });

    }
}

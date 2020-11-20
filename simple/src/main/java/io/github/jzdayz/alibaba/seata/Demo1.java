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
import io.undertow.util.Headers;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class Demo1 {


    public static void main(String[] args) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/test1?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("123123123");
        ds.setAutoCommit(false);
        DataSourceProxy dataSourceProxy = new DataSourceProxy(ds);

        TMClient.init("test", "my_test_tx_group");
        RMClient.init("test", "my_test_tx_group");
        Undertow server = Undertow.builder()
                .addHttpListener(9001, "localhost")
                .setHandler(exchange -> {
                    handler(dataSourceProxy);
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send("Hello World");
                }).build();
        server.start();
    }

    private static void handler(DataSourceProxy ds) {
        GlobalTransaction tx = GlobalTransactionContext.getCurrentOrCreate();
        try {
            tx.begin(60000,"test111");
        } catch (TransactionException e) {
            e.printStackTrace();
        }
        final String xid = RootContext.getXID();
        Utils.closeDoing(ds::getConnection, (cnn) -> {
            Utils.closeDoing(cnn::createStatement, (statement) -> {
                statement.execute("insert test values (null,'DEMO1')");

                HttpGet a = new HttpGet("http://localhost:9002");
                a.setHeader("xid", xid);
                CloseableHttpClient httpClient = HttpClients.createDefault();
                httpClient.execute(a);
            });
            cnn.commit();
            tx.commit();
        });
    }

}

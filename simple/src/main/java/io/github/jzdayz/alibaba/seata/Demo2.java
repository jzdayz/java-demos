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
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/app2?useSSL=false");
        ds.setUsername("root");
        ds.setPassword("JKLjkl123");
        ds.setAutoCommit(false);
        DataSourceProxy dataSourceProxy = new DataSourceProxy(ds);

        //init seata;
        TMClient.init("test1", "my_test_tx_group");
        RMClient.init("test1", "my_test_tx_group");
        Undertow server = Undertow.builder()
                .addHttpListener(8081, "localhost")
                .setHandler(exchange -> {
                    final HeaderMap requestHeaders = exchange.getRequestHeaders();
                    final HeaderValues xid = requestHeaders.get("xid");
                    final String xidStr = xid.getFirst();
                    handler(xidStr,dataSourceProxy);
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send("Hello World");
                }).build();
        server.start();
    }

    private static void handler(String xid, DataSourceProxy ds) throws TransactionException {

        //trx
        RootContext.bind(xid);
        GlobalTransaction tx = GlobalTransactionContext.getCurrentOrCreate();
        try {
            tx.begin(60000, "testBiz");
            final ConnectionProxy connection1 = ds.getConnection();
            final Statement statement = connection1.createStatement();
            statement.execute("insert app2 values (null,'APP2')");
            if (1==1)
            throw new RuntimeException();
            connection1.rollback();
            tx.rollback();
        } catch (Exception exx) {
            tx.rollback();
        }

    }
}

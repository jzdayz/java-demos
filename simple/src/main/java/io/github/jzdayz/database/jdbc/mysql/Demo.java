package io.github.jzdayz.database.jdbc.mysql;

import com.zaxxer.hikari.HikariDataSource;
import io.github.jzdayz.jdk.Xml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) {
        HikariDataSource hikariDataSource = null;
        try {
            final String user = "root";
            final String pwd = "123123123";
            final String className = "com.p6spy.engine.spy.P6SpyDriver";
            final String url = "jdbc:p6spy:mysql://jzdayz.club:3306/test?useSSL=false&rewriteBatchedStatements=true";
            hikariDataSource = new HikariDataSource();
            hikariDataSource.setUsername(user);
            hikariDataSource.setPassword(pwd);
            hikariDataSource.setJdbcUrl(url);
            hikariDataSource.setDriverClassName(className);

            try (
                    Connection connection = hikariDataSource.getConnection();
            ) {
                // The sql is "insert into test values (null,'11111111')"
                String sql = Xml.xmlSql("mysqlTest");
                // 这里测试mysql的batch功能
                // addBatch(sql) 是将sql逐个进行执行
                // addBatch() 在默认情况下，是伪batch功能(逐个执行)，在启动参数rewriteBatchedStatements=true的时候
                // mysql才是真正的batch执行，insert语句会变成 insert into values(null,'1'),(null,'2')
                // 而其他的语句会变成
                // update table set a = 1 where id = 1;
                // update table set a = 2 where id = 2;
                // update table set a = 3 where id = 3
                // 即为一次发送sql，用分号隔开
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                int[] ids = new int[]{77,78,79,80};
                for (int i = 0; i < 4; i++) {
                    preparedStatement.setString(1,"sdfdsfsd");
                    preparedStatement.setInt(2,ids[i]);
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            if (hikariDataSource != null) {
                hikariDataSource.close();
            }
        }
    }
}

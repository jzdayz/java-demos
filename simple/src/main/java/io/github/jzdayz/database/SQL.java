package io.github.jzdayz.database;

import com.zaxxer.hikari.HikariDataSource;
import io.github.jzdayz.jdk.Xml;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("DuplicatedCode")
public class SQL {

    public static void main(String[] args) {
//        mail1();
        mail2();
    }

    private static void mail2() {
        HikariDataSource hikariDataSource = null;
        try {
            final String user = "root";
            final String pwd = "123123123";
            final String className = "com.p6spy.engine.spy.P6SpyDriver";
            final String url = "jdbc:p6spy:mysql://jzdayz.club:3306/test?useSSL=false";
            hikariDataSource = new HikariDataSource();
            hikariDataSource.setUsername(user);
            hikariDataSource.setPassword(pwd);
            hikariDataSource.setJdbcUrl(url);
            hikariDataSource.setDriverClassName(className);

            try (
                    Connection connection = hikariDataSource.getConnection();
                    ){
                // The sql is "insert into test values (null,'11111111')"
                String sql = Xml.xmlSql("insert");
                Statement statement = connection.createStatement();
                for (int i = 0; i < 2; i++) {
                    statement.addBatch(sql);
                }
                statement.executeBatch();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            if (hikariDataSource!=null){
                hikariDataSource.close();
            }
        }
    }

    private static void mail1() {
        HikariDataSource hikariDataSource = null;
        try {
            final String user = "root";
            final String pwd = "123123123";
            final String className = "com.p6spy.engine.spy.P6SpyDriver";
            final String url = "jdbc:p6spy:mysql://jzdayz.club:3306/test?useSSL=false";
            hikariDataSource = new HikariDataSource();
            hikariDataSource.setUsername(user);
            hikariDataSource.setPassword(pwd);
            hikariDataSource.setJdbcUrl(url);
            hikariDataSource.setDriverClassName(className);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
            List<Map<String, Object>> maps = jdbcTemplate.queryForList(null);
            System.out.println(maps);
        }finally {
            if (hikariDataSource!=null){
                hikariDataSource.close();
            }
        }

    }


}

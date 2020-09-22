package io.github.jzdayz.database;

import com.zaxxer.hikari.HikariDataSource;
import io.github.jzdayz.jdk.Xml;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("DuplicatedCode")
public class SQL {

    public static void main(String[] args) {
        main5();
    }

    private static void main5() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("ding!@");
        hikariDataSource.setJdbcUrl("jdbc:sqlserver://");
        hikariDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        JdbcTemplate r = new JdbcTemplate(hikariDataSource);
        try {
            final String prefix = "project";
            List<String> s1 = r.queryForList(Xml.xmlSql("s1"), String.class);
            String rs = s1.stream().map(k -> prefix + "." + k + " as '" + prefix + "." + k + "' ").collect(Collectors.joining(","));
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
            hikariDataSource.close();
        }
    }

    private static void main4() {
        HikariDataSource hikariDataSource;
        final String user = "";
        final String pwd = "1qaz@wsx";
        final String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        final String url = "jdbc:sqlserver://";
        hikariDataSource = new HikariDataSource();
        hikariDataSource.setUsername(user);
        hikariDataSource.setPassword(pwd);
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setDriverClassName(className);
        JdbcTemplate r = new JdbcTemplate(hikariDataSource);
        try {
            List<Map<String, Object>> check1 = r.queryForList(Xml.xmlSql("check1"));
            List<Map<String, Object>> check2 = r.queryForList(Xml.xmlSql("check2"));

            Set<Object> d = check1.stream().map(k -> k.get("wddepid")).collect(Collectors.toSet());
            List<Object> rs = check2.get(0).values().stream().filter(k -> !d.contains(k)).collect(Collectors.toList());
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
            hikariDataSource.close();
        }

    }

    private static void main3() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        HikariDataSource hikariDataSource1 = new HikariDataSource();
        try {
            hikariDataSource.setUsername("");
            hikariDataSource.setPassword("1qaz@wsx");
            hikariDataSource.setJdbcUrl("");
            hikariDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            hikariDataSource1.setUsername("wonders_hr");
            hikariDataSource1.setPassword("");
            hikariDataSource1.setJdbcUrl("");
            hikariDataSource1.setDriverClassName("oracle.jdbc.OracleDriver");


            JdbcTemplate sqlServer = new JdbcTemplate(hikariDataSource);
            List<Map<String, Object>> maps1 = sqlServer.queryForList(Objects.requireNonNull(Xml.xmlSql("sql1")));
            JdbcTemplate oracle = new JdbcTemplate(hikariDataSource1);
            List<Map<String, Object>> maps2 = oracle.queryForList(Objects.requireNonNull(Xml.xmlSql("sql2")));

            Map<Object, Map<String, Object>> map1 = maps1.stream().collect(Collectors.toMap(k -> k.get("empcode"), Function.identity()));
            Map<Object, Map<String, Object>> map2 = maps2.stream().collect(Collectors.toMap(k -> k.get("ST_WORKID"), Function.identity(), (x, y) -> x));


            StringBuilder sb = new StringBuilder();
            map1.forEach((x, y) -> {
                if (map2.get(x) == null) {
                    sb.append("'").append(x).append("'").append(",");
                }
            });
            sb.deleteCharAt(sb.length() - 1);
            System.err.println(sb);


        } finally {
            hikariDataSource.close();
            hikariDataSource1.close();
        }
    }

    private static void main2() {
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
            ) {
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
        } finally {
            if (hikariDataSource != null) {
                hikariDataSource.close();
            }
        }
    }

    private static void main1() {
        HikariDataSource hikariDataSource = null;
        try {
            final String user = "root";
            final String pwd = "JKLjkl123";
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
        } finally {
            if (hikariDataSource != null) {
                hikariDataSource.close();
            }
        }

    }


}

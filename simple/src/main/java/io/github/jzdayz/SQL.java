package io.github.jzdayz;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class SQL {

    public static void main(String[] args) {
        mail1();
    }

    private static void mail1() {
        HikariDataSource hikariDataSource = null;
        try {
            final String user = "";
            final String pwd = "";
            final String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            final String url = "";
            hikariDataSource = new HikariDataSource();
            hikariDataSource.setUsername(user);
            hikariDataSource.setPassword(pwd);
            hikariDataSource.setJdbcUrl(url);
            hikariDataSource.setDriverClassName(className);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
            List<String> strings = jdbcTemplate.queryForList(SQL2, String.class);
            System.err.println(strings.stream().map(k->"'"+k+"'").collect(Collectors.joining(",")));
        }finally {
            if (hikariDataSource!=null){
                hikariDataSource.close();
            }
        }

    }


    final static String SQL1 = "select  * from sm_v";
    final static String SQL2 = "select empcode from  where flag_sm = '1'";
}

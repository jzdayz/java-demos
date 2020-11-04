package io.github.jzdayz.hikari;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Demo {
    public static void main(String[] args) {
        try (HikariDataSource ds = new HikariDataSource()) {
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/test?useSSL=false");
            ds.setUsername("root");
            ds.setPassword("JKLjkl123");
            try (
                    Connection connection = ds.getConnection();
            ) {
                System.out.println(connection.getMetaData().getDatabaseProductName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

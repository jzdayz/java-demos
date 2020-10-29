package io.github.jzdayz.mybatis;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class JdbcDemo {
    public static void main(String[] args) {
        try (HikariDataSource ds = new HikariDataSource()) {
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/test?useSSL=false");
            ds.setUsername("root");
            ds.setPassword("JKLjkl123");
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `test`.`test`(`id`, `name`) VALUES (null, 'name3')", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            try (
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys()
                    ){
                String id = generatedKeys.getString("id");
                System.out.println(id);
            };

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}

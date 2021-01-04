package io.github.jzdayz.database.jdbc.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo3 {
    public static void main(String[] args) {
        MysqlDataSource m = new MysqlDataSource();
        m.setUser("root");
        m.setPassword("123123123");
        m.setUrl("jdbc:mysql://localhost:3306/all?useSSL=false&useCursorFetch=true&defaultFetchSize=100");
        try (
                Connection connection = m.getConnection();
                ){
            Statement statement = connection.createStatement();
            statement.execute("select id from test");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                int string = resultSet.getInt(1);
                System.out.println(string);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

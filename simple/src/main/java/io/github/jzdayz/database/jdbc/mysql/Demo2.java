package io.github.jzdayz.database.jdbc.mysql;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo2 {
    public static void main(String[] args) {
        HikariDataSource hikariDataSource = null;
        try {
            final String user = "root";
            final String pwd = "123123123";
            final String className = "com.mysql.jdbc.Driver";
            final String url = "jdbc:mysql://localhost:3306/all?useSSL=false&rewriteBatchedStatements=true";
            hikariDataSource = new HikariDataSource();
            hikariDataSource.setUsername(user);
            hikariDataSource.setPassword(pwd);
            hikariDataSource.setJdbcUrl(url);
            hikariDataSource.setDriverClassName(className);
            hikariDataSource.setMaximumPoolSize(30);
            hikariDataSource.setMinimumIdle(30);

            AtomicInteger in = new AtomicInteger();



            CountDownLatch cdl = new CountDownLatch(30);
            for (int j = 0; j < 30; j++) {
                HikariDataSource finalHikariDataSource = hikariDataSource;
                new Thread(() -> {
                    for (int n = 0; n < 40; n++) {
                        try (
                                Connection connection = finalHikariDataSource.getConnection()
                        ) {
                            connection.setAutoCommit(false);
                            PreparedStatement preparedStatement = connection.prepareStatement("insert into test values (null,?,?)");
                            for (int i = 0; i < 500; i++) {

                                preparedStatement.setString(1, "1" );
                                preparedStatement.setString(2, a() );
                                preparedStatement.addBatch();
                            }
                            preparedStatement.executeBatch();
                            connection.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    cdl.countDown();
                }).start();
            }
            cdl.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            hikariDataSource.close();
        }
    }

    private static String a(){
        Random random = new Random();
        int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写还是小写
        return new String(new char[]{(char)(choice + random.nextInt(26))});
    }

}

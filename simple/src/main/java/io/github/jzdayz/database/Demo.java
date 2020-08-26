package io.github.jzdayz.database;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

@Slf4j
public class Demo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        log.info("start");
        try (
                Connection connection =
                        DriverManager.getConnection(
                                "jdbc:mysql://rm-bp1k3o60fe2t8nh5tqo.mysql.rds.aliyuncs.com:3306/test?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false",
                                "jzdayz", "Aa123123123");
                Statement statement = connection.createStatement();
        ) {

            connection.setAutoCommit(false);

            statement.execute("update `order` set column_6 = '11111' where id =8");

            if (Objects.equals("ok", scanner.next())) {
                connection.commit();
                log.info("commit");
            } else {
                connection.rollback();
                log.error("rollback");
            }

        } catch (Exception e) {
            log.error("error", e);
        }
    }
}

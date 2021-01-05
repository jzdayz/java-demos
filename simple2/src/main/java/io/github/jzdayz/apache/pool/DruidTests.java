package io.github.jzdayz.apache.pool;

import com.alibaba.druid.filter.logging.CommonsLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class DruidTests {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/all?useSSL=false";
        String username = "root";
        String password = "123123123";
        DruidDataSource dds = new DruidDataSource();
        dds.setUsername(username);
        dds.setPassword(password);
        dds.setUrl(url);
        dds.init();
        StatFilter sf = new StatFilter();
        sf.setLogSlowSql(true);
        sf.setSlowSqlMillis(1L);
        dds.setProxyFilters(Arrays.asList(sf));

        try (
                DruidPooledConnection connection = dds.getConnection();
                ){
            Statement statement = connection.createStatement();
            boolean execute = statement.execute("select * from test");
            System.out.println(execute);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package io.github.jzdayz.database;

import cn.hutool.core.convert.Convert;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DB {

    public static void main(String[] args) {
        HikariDataSource d1 = new HikariDataSource();
        d1.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        HikariDataSource d2 = new HikariDataSource();
        d2.setDriverClassName("com.mysql.jdbc.Driver");

        if (!compare(
                d1,"select project_num,count(1) as c from V_log_info GROUP BY project_num ORDER BY project_num",
                d2,"select project_num,count(1) as c from gh_v_log_info GROUP BY project_num ORDER BY project_num")){
            System.out.println("不相同");
            return;
        }
        System.out.println("相同");

    }

    public static boolean compare(DataSource d1,String sql1,DataSource d2,String sql2){
        JdbcTemplate j1 = new JdbcTemplate(d1);
        List<Map<String, Object>> map1 = j1.queryForList(sql1);

        JdbcTemplate j2 = new JdbcTemplate(d2);
        List<Map<String, Object>> map2 = j2.queryForList(sql2);

        Map<String, String> m1 = map1.stream().collect(Collectors.toMap(k -> Convert.toStr(k.get("project_num")), k -> Convert.toStr(k.get("c"))));
        Map<String, String> m2 = map2.stream().collect(Collectors.toMap(k -> Convert.toStr(k.get("project_num")), k -> Convert.toStr(k.get("c"))));

        for (String key : m1.keySet()) {
            String v1 = m1.get(key);
            String v2 = m2.get(key);
            if (!Objects.equals(v2,v1)){
                System.out.println(1);
            }

        }
        return true;



//        if (map1.size() != map2.size()){
//            return false;
//        }
//
//        int index = 0;
//        for (Map<String, Object> entity : map1) {
//            Map<String, Object> entity2 = map2.get(index);
//            for (String key : entity.keySet()) {
//                Object a = Convert.toStr(entity.get(key));
//                Object b = Convert.toStr(entity2.get(key));
//
//                if (!Objects.equals(a, b)){
//                    return false;
//                }
//            }
//            index++;
//        }
//        return true;

    }

}

package io.github.jzdayz.mybatis;

import com.alibaba.excel.EasyExcel;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JdbcDemo {
    public static void main(String[] args) {
        String costType = "通讯费,外埠差旅费,市内租赁费,市内住宿费,市内交通费,办公杂费,招待费,专家费,测试费,资质管理费,专线费,会务费,业务宣传费,工作餐费";
        Arrays.stream(costType.split(","))
                .forEach(e-> System.out.println("insert into pm_cost_type values (null,'"+e+"');"));
    }

    private static void test3() {
        List<LinkedHashMap<Integer, String>> sheet1 = EasyExcel.read("/Users/huqingfeng/Downloads/abc.xlsx")
                .sheet("Grid Results")
                .doReadSync();

        String areaSql = "select * from pm_area where name = ?";
        String postSql = "select * from pm_post where name = ?";
        String personPlanSql = "insert into pm_person_plan_cost values (null,'%s','%s','%s','%s','%s','%s');";

        try (HikariDataSource ds = new HikariDataSource()) {
            ds.setJdbcUrl("");
            ds.setUsername("");
            ds.setPassword("");
            JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

            for (LinkedHashMap<Integer, String> obj : sheet1) {
                String area = obj.get(0);
                String post = obj.get(1);
                List<Map<String, Object>> areaMap = jdbcTemplate.queryForList(areaSql, area);
                int s1 = areaMap.size();
                if ((s1 == 0 || s1 > 1) && !"烟台".equals(area)) {
                    throw new RuntimeException(area);
                }
                List<Map<String, Object>> postMap = jdbcTemplate.queryForList(postSql, post);
                int s2 = postMap.size();
                if (s2 == 0 || s2 > 1) {
                    throw new RuntimeException(post);
                }

                Object areaId = areaMap.size() == 0 ? "-1" : areaMap.get(0).get("id");
                Object postId = postMap.get(0).get("id");

                System.out.printf((personPlanSql) + "%n", areaId, area, postId, post, obj.get(2), obj.get(3));


            }


        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    private static void test2() {
        String area = "上海,浙江,江苏,安徽,山东,江西,福建,河南,湖北,湖南,北京,天津,河北,山西,陕西,辽宁,吉林,黑龙江,重庆,四川,广东,广西,海南,贵州,云南,内蒙古,甘肃,青海,新疆,宁夏,西藏,香港,澳门";
        String post = "项目经理（项目主管）,产品经理,系统分析师（架构师）,界面设计师（创意师）,软件开发工程师,软件实施工程师,测试工程师,集成系统工程师,工程实施工程师,维护工程师,数据分析工程师,数据治理工程师," +
                "算法工程师,大数据开发工程师,大数据运维工程师,人工智能研发工程师,云计算工程师,安全工程师,行业顾问,售前工程师,售后工程师,技术顾问," +
                "数据运营工程师,客户服务工程师,运营推广工程师,客户服务经理（主管）,业务经理,保险理赔师,医学主管";

        String sqlArea = "insert into pm_area values (null,'%s');";
        String sqlPost = "insert into pm_post values (null,'%s');";

        Arrays.stream(area.split(","))
                .forEach(e -> System.out.printf((sqlArea) + "%n", e));

        Arrays.stream(post.split(","))
                .forEach(e -> System.out.printf((sqlPost) + "%n", e));
    }

    private static void test1() {
        try (HikariDataSource ds = new HikariDataSource()) {
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/test?useSSL=false");
            ds.setUsername("root");
            ds.setPassword("JKLjkl123");
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `test`.`test`(`id`, `name`) VALUES (null, 'name3')", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            try (
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys()
            ) {
                String id = generatedKeys.getString("id");
                System.out.println(id);
            }
            ;

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}

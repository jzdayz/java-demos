package io.github.jzdayz.alibaba.easyexcel;

import com.alibaba.excel.EasyExcel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"unchecked", "SuspiciousMethodCalls"})
public class Test1 {
    public static void main(String[] args) {
        final List<Object> rs = EasyExcel.read("/Users/huqingfeng/Downloads/8.xlsx").sheet("Grid Results").doReadSync();
        // 地区对应的岗位
        final Map<String, List<AreaPost>> areaPost = rs.stream()
                .map(k -> (LinkedHashMap<String, Object>) k)
                .map(k-> new AreaPost((String) k.get(0),(String) k.get(1)))
                .collect(Collectors.groupingBy(AreaPost::getArea));


        String area = "上海,浙江,江苏,安徽,山东,江西,福建,河南,湖北,湖南,北京,天津,河北,山西,陕西,辽宁,吉林,黑龙江,重庆,四川,广东,广西,海南,贵州,云南,内蒙古,甘肃,青海,新疆,宁夏,西藏,香港,澳门";
        String post = "项目经理（项目主管）,产品经理,系统分析师（架构师）,界面设计师（创意师）,软件开发工程师,软件实施工程师,测试工程师,集成系统工程师,工程实施工程师,维护工程师,数据分析工程师,数据治理工程师," +
                "算法工程师,大数据开发工程师,大数据运维工程师,人工智能研发工程师,云计算工程师,安全工程师,行业顾问,售前工程师,售后工程师,技术顾问," +
                "数据运营工程师,客户服务工程师,运营推广工程师,客户服务经理（主管）,业务经理,保险理赔师,医学主管";

        {
          // 只比一下岗位
            List<String> postNo = new ArrayList<>();
            final Set<String> a1 = areaPost.values().stream().flatMap(k -> k.stream().map(AreaPost::getPost)).collect(Collectors.toSet());
            for (String a2 : post.split(",")) {
                if (!a1.contains(a2)){
                    postNo.add(a2);
                }
            }
            System.out.println("岗位对比结果："+postNo);
        }

        final String[] areaLs = area.split(",");
        final String[] postLs = post.split(",");

        // 这个是标准，根据这个检查上面遗漏的
        final Map<String, List<AreaPost>> standard = Arrays.stream(areaLs)
                .flatMap(k -> {
                    List<AreaPost> aps = new ArrayList<>();
                    for (String p : postLs) {
                        aps.add(new AreaPost(k, p));
                    }
                    return aps.stream();
                })
                .collect(Collectors.groupingBy(AreaPost::getArea));


        List<String> areaNo = new ArrayList<>();
        standard.forEach((areaKey,areaPostValue)->{
            // 一个个检查
            final List<AreaPost> areaPosts = areaPost.get(areaKey);
            if (areaPosts == null){
                areaNo.add(areaKey);
                return;
            }
            final Set<String> ap = areaPosts.stream().map(AreaPost::getPost).collect(Collectors.toSet());
            for (AreaPost app : areaPostValue) {
                if(!ap.contains(app.getPost())){
                    System.out.printf("地域[%s]不存在岗位{%s}%n",areaKey,app.getPost());
                }
            }

        });



        System.out.printf("地域%s不存在",areaNo);



    }

    @Data
    @AllArgsConstructor
    private static class AreaPost{
        private String area;
        private String post;
    }
}

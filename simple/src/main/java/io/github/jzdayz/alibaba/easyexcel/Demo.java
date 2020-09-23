package io.github.jzdayz.alibaba.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Demo {

    static final String path = "/Users/huqingfeng/Downloads/1111.xlsx";

    public static void main(String[] args) {
        // 这里 只要，然后读取第一个sheet 同步读取会自动finish
        NoModelDataListener readListener = new NoModelDataListener();
        EasyExcel.read(path, readListener).sheet().doRead();

        List<Map<Integer, String>> list = readListener.getList();

        final String tmp = "UPDATE TBL_PEOPLEINFO set ST_WORKID = '%s' where ST_WORKID = '%s';";
        final String tmp1 =
                "UPDATE TBL_DEPHISINFO set ST_COMPANYNAME_LDGX = '' , ST_COMPANY_LDGX = '' where ST_ID = (\n"
                        +
                        "\tselect ST_ID from TBL_DEPHISINFO where ST_PERSONID = (\n" +
                        "\t\tselect ST_ID from TBL_PEOPLEINFO where ST_WORKID = '%s'\n" +
                        "\t)\n" +
                        ");";

        // 修改工号
        System.out.println();
        list.forEach(map -> {
            ArrayList<String> s = new ArrayList<>(map.values());
            System.err.println(String.format(tmp, s.get(0), s.get(2)));
        });

        // 修改所属公司
        list.forEach(map -> {
            ArrayList<String> s = new ArrayList<>(map.values());
            System.err.println(String.format(tmp1, s.get(2)));
        });


    }
}

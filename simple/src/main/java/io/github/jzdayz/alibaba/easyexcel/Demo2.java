package io.github.jzdayz.alibaba.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.LinkedHashMap;

@SuppressWarnings("unchecked")
public class Demo2 {
    public static void main(String[] args) {
        String sql = "insert into old_pay_data values (null,%s,%s,%s);";
        EasyExcel.read("/Users/huqingfeng/Downloads/老报销系统2020年实际费用数据.xlsx")
                .doReadAllSync()
                .stream()
                .map(k -> (LinkedHashMap<Integer, String>) k)
                .forEach(e ->
                        System.out.printf(
                                (sql) + "%n",
                                value(e.get(0)),
                                value(e.get(1)),
                                value(e.get(2))
                        )
                );
    }

    private static String value(String data) {
        if (data == null) {
            return null;
        }
        return "'" + data + "'";
    }

}

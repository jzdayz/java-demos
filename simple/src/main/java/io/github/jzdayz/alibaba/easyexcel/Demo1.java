package io.github.jzdayz.alibaba.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Demo1 {
    public static void main(String[] args) {
        String tmp = "update TBL_PEOPLEINFO set ST_WORKID = '%s' where ST_WORKID = '%s';";
        List<LinkedHashMap<Integer, String>> sheet1 = EasyExcel.read("/Users/huqingfeng/Downloads/工作簿1.xlsx")
                .sheet("Sheet1")
                .headRowNumber(0)
                .doReadSync();
        sheet1.forEach(k-> System.out.printf((tmp) + "%n",k.get(1),k.get(0)));
    }

    private static void test1() {
        List<LinkedHashMap<Integer, String>> sheet1 = EasyExcel.read("/Users/huqingfeng/Downloads/工作簿1.xlsx")
                .sheet("Sheet1")
                .headRowNumber(0)
                .doReadSync();

        System.out.println(sheet1.size());

        System.out.println("(" + sheet1.stream()
                .map(k -> "'"+k.get(0)+"'")
                .collect(Collectors.joining(",")) + ")");
    }
}

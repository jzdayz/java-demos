package io.github.jzdayz;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 *  将gbk转utf-8
 */
@CommonsLog
public class Transform {
    public static void main(String[] args) {
        String dir = "/Users/huqingfeng/Downloads/src";
        File file = new File(dir);
        if (!file.isDirectory()){
            return ;
        }
        file(file);
    }
    public static void file(File file){
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if (files==null){
                return;
            }
            for (File f : files) {
                file(f);
            }
            return;
        }
        if (!file.getName().endsWith("java")){
            log.info(file.getPath() + "必须是java文件");
            return;
        }
        if(file.getName().startsWith(".")){
            log.info(file.getPath() + "隐藏文件");
            return ;
        }
        if (Objects.equals(charsetType(file.getPath()),"utf-8")){
            log.info(file.getPath() + "已经是utf-8");
            return;
        }
        String gbkStr = "";
        try (
                FileInputStream fileInputStream = new FileInputStream(file)
        ){
            gbkStr =  StreamUtils.copyToString(fileInputStream, Charset.forName("GBK"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileOutputStream outputStream = new FileOutputStream(file)
        ){
            StreamUtils.copy(gbkStr, StandardCharsets.UTF_8,outputStream);
            log.info("装换文件 -> "+file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static String charsetType(String fileName) {
        int len1 = 0,len2 = 0;
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))
                ){
            String str=reader.readLine();
            len1=str.length();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "gbk"))
        ){
            String str=reader.readLine();
            len2=str.length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(len1<=len2){
            return "utf-8";
        }
        else{
            return "gbk";
        }
    }

}

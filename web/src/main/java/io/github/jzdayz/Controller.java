package io.github.jzdayz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Api("测试")
@RestController
public class Controller {


    @GetMapping("/test/date")
    public Object testDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
        return date;
    }

    @GetMapping("/test/date2")
    public Object testDate2(@SimpleDate Date date){
        return date;
    }

    @ApiOperation("api测试")
    @GetMapping
    public Object test(@ApiParam(name = "名称",required = true) String name) {
        return name;
    }

    /**
     *  下载文件
     * @return
     */
    @GetMapping("/test1")
    public ResponseEntity<StreamingResponseBody> test1() {
        ClassPathResource res = new ClassPathResource("/application.yaml");
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=application.yaml");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        return new ResponseEntity<>(
                (output) -> {
                    try (
                            InputStream inputStream = res.getInputStream();
                            ){
                        StreamUtils.copy(inputStream, output);
                    }
                }, headers, HttpStatus.OK);
    }

    /**
     *  操作zip文件的内容
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/test2")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> test2(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        ZipInputStream zipInputStream = new ZipInputStream(
                new ByteArrayInputStream(bytes), StandardCharsets.UTF_8
        );
        ZipEntry entry;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream out = new ZipOutputStream(byteArrayOutputStream);
        while ((entry = zipInputStream.getNextEntry()) != null){
            if (filter(entry.getName())){
                continue;
            }

            System.out.println("fileName:" + entry.getName());
            String data = StreamUtils.copyToString(zipInputStream, Charset.defaultCharset());

            System.out.println(data);
            ZipEntry e = new ZipEntry(entry.getName());
            out.putNextEntry(e);
            if (!e.isDirectory()) {
                StreamUtils.copy((data + "UPDATE").getBytes(), out);
                System.out.println("写入数据");
            }
        }
        out.flush();
        out.close();


        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ab.zip");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        return new ResponseEntity<>(
                (output) -> {
                    try{
                        StreamUtils.copy(byteArrayOutputStream.toByteArray(), output);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }, headers, HttpStatus.OK);

    }

    private boolean filter(String name){
        if (name.contains(".DS_Store") || name.startsWith("__MACOSX")){
            return true;
        }
        return false;
    }

}

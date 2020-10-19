package io.github.jzdayz.simple.api;

import io.github.jzdayz.simple.config.SimpleDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.util.concurrent.ListenableFutureTask;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Api("测试")
@RestController
@Slf4j
@RequestMapping("api")
public class Controller {


    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            1, 1, 1L, TimeUnit.HOURS, new ArrayBlockingQueue<>(100)
    );

    @GetMapping("/async")
    public Object async() {
        log.info(" async invoke ");
        ListenableFutureTask<Object> objectListenableFutureTask = new ListenableFutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(1L);

            log.info(" async handle ");
            return Arrays.asList("111", "sfsff");
        });
        THREAD_POOL_EXECUTOR.submit(objectListenableFutureTask);
        return objectListenableFutureTask;
    }

    @GetMapping("/test/date")
    public Object testDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        return date;
    }

    @GetMapping("/test/date2")
    public Object testDate2(@SimpleDate Date date) {
        return date;
    }

    @ApiOperation("api测试")
    @GetMapping
    public Object test(@ApiParam(name = "名称", required = true) String name) {
        return name;
    }

    /**
     * 下载文件
     *
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
                    ) {
                        StreamUtils.copy(inputStream, output);
                    }
                }, headers, HttpStatus.OK);
    }


    /**
     * 下载文件
     *
     * @return
     */
    @RequestMapping("/dl")
    public ResponseEntity<StreamingResponseBody> dl(String path) throws UnsupportedEncodingException {
        PathResource res = new PathResource(path);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+ URLEncoder.encode(res.getFilename(),"UTF-8"));
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        return new ResponseEntity<>(
                (output) -> {
                    try (
                            InputStream inputStream = res.getInputStream();
                    ) {
                        StreamUtils.copy(inputStream, output);
                    }
                }, headers, HttpStatus.OK);
    }

    /**
     * 操作zip文件的内容
     *
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
        while ((entry = zipInputStream.getNextEntry()) != null) {
            if (filter(entry.getName())) {
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
                    try {
                        StreamUtils.copy(byteArrayOutputStream.toByteArray(), output);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, headers, HttpStatus.OK);

    }

    private boolean filter(String name) {
        if (name.contains(".DS_Store") || name.startsWith("__MACOSX")) {
            return true;
        }
        return false;
    }

}

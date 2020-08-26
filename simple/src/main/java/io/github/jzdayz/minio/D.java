package io.github.jzdayz.minio;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class D {
    public static void main(String[] args) throws Exception {
        MinioClient minioClient = new MinioClient("http://:9000/", "", "");
        ObjectStat imagedb = minioClient.statObject("imagedb", "4b71eefeca2c421884f037f6550b0b56.jpg");
        Files.deleteIfExists(Paths.get(new URL("file:///Users/huqingfeng/Downloads/a.jpg").toURI()));
        try (
                InputStream upload = minioClient.getObject("imagedb", "c799b85962d14ed0bc2f3fb9f2149ef4.jpg");
                FileOutputStream file = new FileOutputStream(new File("/Users/huqingfeng/Downloads/a.jpg"))
        ) {
            StreamUtils.copy(upload, file);
        }


    }
}

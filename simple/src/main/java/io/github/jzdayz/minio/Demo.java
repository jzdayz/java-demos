package io.github.jzdayz.minio;

import io.minio.ErrorCode;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;

public class Demo {

    public static void main(String[] args) throws Exception {
        MinioClient minioClient = new MinioClient("http://localhost:9000", "123123123", "123123123");
        boolean found = false;
        try {
            minioClient.statObject("test", "b.zip");
            found = true;
        } catch (ErrorResponseException e) {
            ErrorCode code = e.errorResponse().errorCode();
            if (code == ErrorCode.NO_SUCH_KEY || code == ErrorCode.NO_SUCH_OBJECT) {
            } else {
                throw e;
            }
        }
        System.out.println(found);
        if (found) {

        } else {

        }
    }
}

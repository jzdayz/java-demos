package io.github.jzdayz.flux.api;

import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RequestMapping("api")
@Controller
public class ApiController {

    private static final DefaultDataBufferFactory DATA_BUFFER_FACTORY = new DefaultDataBufferFactory();

    @GetMapping("download")
    public void download(ServerHttpResponse response, String path) {
        FileSystemResource fileSystemResource = new FileSystemResource(path);
        long length = fileSystemResource.getFile().length();
        long b = DataSize.ofMegabytes(2).toBytes();
        try (InputStream inputStream = fileSystemResource.getInputStream()) {
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().set(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_OCTET_STREAM.toString());
            response.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=out.xlsx");
            for (int i = 0; i < length; i += b) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                StreamUtils.copyRange(inputStream, out, i, i + b);
                DataBuffer dataBuffer = DATA_BUFFER_FACTORY.allocateBuffer((int) b);
                response.writeWith(Mono.just(dataBuffer.write(out.toByteArray())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

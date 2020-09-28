package io.github.jzdayz.boot.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

@SpringBootApplication
@Slf4j
public class RedisTests {

    static final DefaultRedisScript<String> RS = new DefaultRedisScript<>();

    static {
        RS.setLocation(new ClassPathResource("redis.lua"));
        RS.setResultType(String.class);
    }

    public static void main(String[] args) {
        try (
                ConfigurableApplicationContext context = SpringApplication.run(RedisTests.class, args)
                ){
            test1(context);
        }
    }

    @SuppressWarnings("unchecked")
    private static void test1(ConfigurableApplicationContext context) {
        final String id = "test";

        RedisTemplate<String,String> redisTemplate = context.getBean(StringRedisTemplate.class);
        prepare(redisTemplate,id,100);
        IntStream.range(1,200).parallel().forEach((i)-> {
            int alloc = secKill(redisTemplate, id, 1);
            log.info("消费情况:{}",alloc);
        });
    }

    static int secKill(RedisTemplate<String,String> redisTemplate,String id, int number) {
        String key = getKey(id);
        String alloc =  redisTemplate.execute(RS, Collections.singletonList(key), String.valueOf(number));
        return Integer.parseInt(Objects.requireNonNull(alloc));
    }

    static final String goodsId = "seckill:goods:%s";

    static String getKey(String id) {
        return String.format(goodsId, id);
    }
    static void prepare(RedisTemplate<String,String> redisTemplate,String id, int total) {
        String key = getKey(id);
        Map<String, String> goods = new HashMap<>();
        goods.put("total", String.valueOf(total));
        goods.put("start", "0");
        goods.put("alloc", "0");
        redisTemplate.opsForHash().putAll(key, goods);
    }
}

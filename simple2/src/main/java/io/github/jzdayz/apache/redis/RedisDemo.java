package io.github.jzdayz.apache.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisSetCommands;

public class RedisDemo {
    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost:6379");
        try (
                StatefulRedisConnection<String, String> connection = client.connect()
                ){
            RedisSetCommands<String,String> sync = connection.sync();
            Boolean test1 = sync.sismember("test1", "2");
            System.err.println(test1);
        }
        client.shutdown();


    }
}

package io.github.jzdayz.apache.redis;

import io.lettuce.core.api.sync.RedisStringCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;

public class RedisDemo {
    public static void main(String[] args) {
        RedisClusterClient client = RedisClusterClient.create("redis://localhost:7001");
        try (
                StatefulRedisClusterConnection<String, String> connect = client.connect()
        ){
            RedisStringCommands<String,String> sync = connect.sync();
            System.out.println(sync.get("111"));
        }finally {
            client.shutdown();
        }



    }
}

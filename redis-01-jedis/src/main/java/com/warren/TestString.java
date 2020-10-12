package com.warren;

import redis.clients.jedis.Jedis;

public class TestString {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);

        System.out.println(jedis.set("k1", "v1"));
        System.out.println(jedis.get("k1"));
    }
}

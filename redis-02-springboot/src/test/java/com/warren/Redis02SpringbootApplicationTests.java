package com.warren;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.warren.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis02SpringbootApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        // redisTemplate 用来操作不同的数据类型，api和我们的指令是一样的
        // opsForValue()    操作字符串 类似String
        // opsForList()     操作List
        // opsForSet()
        // opsForHash()
        // opsForZSet()
        // opsForGeo()
        // opsForHyperLogLog()

        // 除了基本的操作，我们常用的方法都可以直接通过redisTemplate操作，比如 事务， 和基本的CRUD

        // 获得redis的连接对象
        //        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        //        connection.flushDb();
        //        connection.flushAll();

        redisTemplate.opsForValue().set("mykey","warren");
        System.out.println(redisTemplate.opsForValue().get("mykey"));
    }

    @Test
    public void test1() throws JsonProcessingException {
        // 真实的开发一般都使用json来传递对象，所有的对象都需要序列化
        User user = new User("warren", 18);
//        String value = new ObjectMapper().writeValueAsString(user);
        redisTemplate.opsForValue().set("user",user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

}

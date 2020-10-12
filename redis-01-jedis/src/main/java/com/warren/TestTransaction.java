package com.warren;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTransaction {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name","warren");

        // 开启事务
        Transaction multi = jedis.multi();
        String result = jsonObject.toJSONString();

        try {
            multi.set("user1",result);
            multi.set("user2",result);
//            int i = 1/0;                          // 程序运行异常 会导致事务失败
            multi.incrBy("user1",1); // 事务中的命令：运行时异常不会导致事务失败，编译型异常会导致事务失败。
            multi.exec();              // 执行事务
        } catch (Exception e) {
            multi.discard();           // 放弃事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();             // 关闭连接
        }
    }
}

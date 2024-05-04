package com.woniuxy.j2e.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {

    @Test
    public void connection() {
//		Jedis jedis=new Jedis("localhost"); // fail

        Jedis jedis = new Jedis("localhost", 6379);
//		Jedis jedis = new Jedis("localhost:6379");
        System.out.println(jedis.ping());
        jedis.close();

//		URI u = URI.create("localhost"); // fail

//		URI u = URI.create("http://localhost:6379"); // ok
//		System.out.println("host: " + u.getHost());
//		System.out.println("port: " + u.getPort());
    }

    @Test
    public void addStr() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("s1", "test from me");
        System.out.println(jedis.get("s1"));
        jedis.close();
    }

    @Test
    public void getStr() {
        Jedis jedis = new Jedis("localhost", 6379);
        String s1 = jedis.get("s1");

        System.out.println(s1);

        jedis.close();
    }

}

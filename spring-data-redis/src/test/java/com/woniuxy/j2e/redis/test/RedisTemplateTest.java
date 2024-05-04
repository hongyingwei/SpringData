package com.woniuxy.j2e.redis.test;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.woniuxy.j2e.redis.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
public class RedisTemplateTest {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void setStr(){
		ValueOperations<String, String> opsForValue = this.stringRedisTemplate.opsForValue();
		opsForValue.set("s1", "get");
	}
	
	@Test
	public void append(){
		ValueOperations<String, String> opsForValue = this.stringRedisTemplate.opsForValue();
		Integer append = opsForValue.append("s1", " a message");
		System.err.println(append);
	}
	
	@Test
	public void getStr(){
		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
		String string = opsForValue.get("s1");
		System.out.println(string);
	}
	
	@Test
	public void keys(){
		Set<String> keys = this.stringRedisTemplate.keys("s*");
		assert keys != null;
		for (String key : keys) {
			System.out.println(this.stringRedisTemplate.opsForValue().get(key));
		}
	}
	
}

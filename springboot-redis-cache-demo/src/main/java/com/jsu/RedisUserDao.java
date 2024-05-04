package com.jsu;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked, rawtypes, unused")
@Repository
public class RedisUserDao {
    final Logger logger = LoggerFactory.getLogger(RedisUserDao.class);

    private static final String KEY = "USER";
    private final HashOperations hashOperations;
    private final ValueOperations opVal;

    public RedisUserDao(RedisTemplate redisTemplate){
        hashOperations = redisTemplate.opsForHash();
        opVal = redisTemplate.opsForValue();
    }

    public void save(User user) {
        hashOperations.put(KEY, user.getId(), user);
    }

    public void saveByName(User user) {
        hashOperations.put(KEY, user.getUsername(), user);
    }

    public void saveMap(User user) {
        Map map = new ObjectMapper().convertValue(user, Map.class);
        System.out.println("map: " + map);
        hashOperations.put(KEY, user.getId(), map);
    }

    public void saveString(String key, String value) {
        hashOperations.put(KEY, key, value);
    }

    public User findById(int id){
        return (User) hashOperations.get(KEY, id);
    }

    public User findByName(String name){
        return (User) hashOperations.get(KEY, name);
    }

    public User findMapById(int id){
        return new ObjectMapper().convertValue(hashOperations.get(KEY, id), User.class);
    }

    public String findString(String key) {
        return (String) hashOperations.get(KEY, key);
    }

    public void delete(String id){
        hashOperations.delete(KEY, id);
    }

    public List findAll(){
        return hashOperations.values(KEY);
    }

    public void update(User user){
        save(user);
    }

    public void testSerializer() {
        opVal.set("admin", "张三疯grandpa");
        hashOperations.put("manager", "wuji", new User(100, "张无忌"));
    }
}

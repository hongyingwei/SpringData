package com.jsu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Optional;

@SpringBootTest(classes = App.class)
public class RedisTest {
    @Resource
    RedisUserDao dao;

    @Resource
    RedisUserRepository repo;

    @Test
    void saveUser() {
        User u = new User(1, "三章");
        dao.save(u);
        System.out.println(dao.findById(1));

        u = new User(2, "SanZHANG");
        dao.saveMap(u);
        System.out.println(dao.findMapById(2));

        dao.saveByName(u);
        System.out.println(dao.findByName(u.getUsername()));

        dao.saveString("zhangsan", "SanZhang");
        System.out.println(dao.findString("zhansan"));
    }

    @Test
    void testRepoSave() {
        for (int i = 0; i < 30; i++) {
            repo.save(new User(i, "user" + i));
        }
    }

    @Test
    void testRepoFind() {
        for (int i = 0; i < 30; i++) {
            Optional<User> u = repo.findById(i);
            System.out.println(u);
        }

        System.out.print("================");

        for (User u: repo.findAll()) {
            System.out.println(u);
        }

        System.out.println(repo.findByUsername("user13"));
        System.out.println(repo.findByUsername("user31"));
    }

    @Test
    void testSerializer() {
        dao.testSerializer();
    }
}

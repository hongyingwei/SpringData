package com.jsu;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RedisUserRepository extends PagingAndSortingRepository<User, Integer> {
    List<User> findByUsername(String username);
}

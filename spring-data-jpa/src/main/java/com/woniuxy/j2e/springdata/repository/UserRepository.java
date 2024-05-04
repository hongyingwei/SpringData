package com.woniuxy.j2e.springdata.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.woniuxy.j2e.springdata.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>{

	List<User> findUserByUserName(String userName);
	
	@Query("select u from User u where u.userName=?1")
	List<User> findUserInfoByUserName(String userName);
	
	@Query(value="select * from t_users where userName=?1",nativeQuery=true)
	List<User> findUserInfoNativeQuery(String userName);
	
	@Modifying
	@Transactional
	@Query("update User set password=?1 where id=?2")
	int modifyingPassword(String password,int id);
	
	User getFirstUserByUserNameAndPassword(String userName,String password);
	
	List<User> findDistinctUserByUserName(String userName);
	
}

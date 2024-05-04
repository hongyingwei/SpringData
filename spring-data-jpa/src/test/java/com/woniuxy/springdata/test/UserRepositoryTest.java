package com.woniuxy.springdata.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.woniuxy.j2e.springdata.Application;
import com.woniuxy.j2e.springdata.entity.User;
import com.woniuxy.j2e.springdata.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	public void save(){
		User user=new User();
		user.setAddress("成都");
		user.setAge(10);
		user.setPassword("1");
		user.setRealName("离歌笑");
		user.setUserName("tlhhup");
		user.setBirthday(new Date());
		
		this.userRepository.save(user);
	}
	
	@Test
	public void update(){
		User user=new User();
		user.setId(1);
		user.setAddress("北京北");
		
		this.userRepository.save(user);
	}
	
	@Test
	public void modifyPwd(){
		this.userRepository.modifyingPassword("zhangsan", 1);
	}
	
	@Test
	public void findAll(){
		Iterable<User> users = this.userRepository.findAll();
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void findUserByUserName(){
		List<User> users = this.userRepository.findUserByUserName("tlhhup");
		System.out.println(users);
	}

	@Test
	public void findUserInfoByUserName(){
		List<User> users = this.userRepository.findUserInfoByUserName("tlhhup");
		System.out.println(users);
	}

	@Test
	public void findDistinctUserByUserName(){
		List<User> users = this.userRepository.findDistinctUserByUserName("tlhhup");
		System.out.println(users);
	}

	@Test
	public void getUserByUserNameAndPassword(){
		User user = this.userRepository.getFirstUserByUserNameAndPassword("tlhhup", "1");
		System.out.println(user);
		user = this.userRepository.getFirstUserByUserNameAndPassword("tlhhup", "2");
		System.out.println(user);
	}
	
	@Test
	public void findUserNative(){
		List<User> users = this.userRepository.findUserInfoNativeQuery("tlhhup");
		System.out.println(users);
	}

	@Test
	public void sortByUserName(){
		Iterable<User> users = this.userRepository.findAll(Sort.by(Direction.DESC, "address"));
		for (User user : users) {
			System.out.println(user);
		}
	}

	@Test
	public void paging(){
//		Page<User> page = this.userRepository.findAll(new PageRequest(0, 5));
		PageRequest paging = PageRequest.of(0, 5, Sort.by(Direction.DESC, "id"));
		Page<User> page = this.userRepository.findAll(paging);
		System.out.println(page.getTotalPages());
		System.out.println(page.getTotalElements());
		System.out.println(page.getSize());
		for (User u: page) {
			System.out.println(u);
		}

		Slice<User> users = this.userRepository.findAll(PageRequest.of(1, 3));
		System.out.println(users.getSize());
		for (User u: users) {
			System.out.println(u);
		}
	}
}

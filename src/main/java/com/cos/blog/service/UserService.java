package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 회원가입(User user) {
		
		userRepository.save(user);
	}
	
	
	//아래코드는 스프링시큐리티를 사용하지않았을때 사용하는 코드이다 전통적인 로그인방식의 코드이다
	/*
	 * @Transactional(readOnly = true) // Select 할 때 트랜잭션 시작, 서비스 종료시 트랜잭션 종료(정합성
	 * 유지) public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
	
}

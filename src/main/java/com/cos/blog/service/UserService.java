package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.persistence.EnumType;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 회원가입(User user) {
		userRepository.save(user);
	}
	
	@Transactional(readOnly=true)  //SELECT할때 트랜잭션 시작,서비스종료시에 트랜잭션 종료되는코드이다 
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
	}

}

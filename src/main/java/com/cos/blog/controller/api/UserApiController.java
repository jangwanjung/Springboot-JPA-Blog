package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
//1
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController: save 호출됨.");
		userService.회원가입(user);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	//시큐리티 라이브러리를 사용하면 아래에있는 로직을 사용하지않아된다
	
	/*
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user,HttpSession session) {
	 * System.out.println("UserApiController: login 호출됨."); User principal
	 * =userService.로그인(user); // principal: 접근주체
	 * 
	 * if(principal != null) { session.setAttribute("principal", principal); }
	 * 
	 * return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
	 */
	
}

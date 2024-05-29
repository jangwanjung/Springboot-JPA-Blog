package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("호출됨");
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user,HttpSession session){
		System.out.println("UserApicontroller:login호출됨");
		User principal=userService.로그인(user);  //principal은 접근주체라는뜻이다
		
		if(principal!=null) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}

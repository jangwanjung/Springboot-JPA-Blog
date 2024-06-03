package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Getter;

@Controller
public class UserController {
	//auth는 인증이 안된사용자들이 출입할수있는 경로를 말하는것이다
	@GetMapping("/auth/joinForm")
	public String joinForm(){
		return "user/joinForm"; 
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm(){
		return "user/loginForm"; 
	}

}

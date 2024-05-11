package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice  //모든 exception이 발생하면 이 클래스로 들어오게 만드는 어노테이션이다
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=Exception.class) //모든 llIegalArgumentException이 발생하면 e로전달된다
	public String handleArgumentException(Exception e) {
		
		return "<h1>"+e.getMessage()+"</h1>";
	}
}

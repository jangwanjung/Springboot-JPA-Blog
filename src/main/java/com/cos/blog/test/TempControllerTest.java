package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller	
public class TempControllerTest {
	
	@GetMapping("/temp/jsp")
	public String TempJsp() {
		System.out.println("tempJsp()");
		return "test";
	}
	
	

}

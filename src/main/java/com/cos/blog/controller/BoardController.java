package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	
	@GetMapping({"","/"}) //아무것도안붙였을때랑 /를 붙였을때만 index.jsp를 열어준다는뜻이다
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {
		
		
		// 주소 WEP-INF/view/index.jsp
		return "index";
	}

}

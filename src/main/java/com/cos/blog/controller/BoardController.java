package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	
	@GetMapping({"","/"}) //아무것도안붙였을때랑 /를 붙였을때만 index.jsp를 열어준다는뜻이다
	//스프링에서 데이터를 넘길떄 Model이 필요하다
	public String index(Model model) {
		model.addAttribute("boards",boardService.글목록()); //model을 통해 글목록을 boards라는이름으로 넘겨준다
		
		// 주소 WEP-INF/view/index.jsp
		return "index"; //@Controller로 데이터를 넘길떄 viewResolver라는게 작동하여 model의정보를 index로 전달한다 
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

}

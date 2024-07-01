package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	
	@GetMapping({"","/"}) //아무것도안붙였을때랑 /를 붙였을때만 index.jsp를 열어준다는뜻이다
	//스프링에서 데이터를 넘길떄 Model이 필요하다
	//@PageableDefault어노테이션으로 게시글의id순으로 최근글을 먼저볼수있게 갯수는 3개
	public String index(Model model,@PageableDefault(size=3,sort="id",direction=Sort.Direction.DESC)Pageable pageable) {
		model.addAttribute("boards",boardService.글목록(pageable)); //model을 통해 글목록을 boards라는이름으로 넘겨준다
		
		// 주소 WEP-INF/view/index.jsp
		return "index"; //@Controller로 데이터를 넘길떄 viewResolver라는게   하여 model의정보를 index로 전달한다 
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.글상세보기(id));
		return "board/detail";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board",boardService.글상세보기(id));
		return "board/updateForm";
		
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

}

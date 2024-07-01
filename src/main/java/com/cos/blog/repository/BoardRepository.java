package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board,Integer>{
	
	//BoardRepository에는 아무것도없어보이지만 아니다 JpaRepository가 다들고있다
	

}

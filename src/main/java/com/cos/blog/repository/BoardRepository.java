package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;

public interface BoardRepository extends JpaRepository<Board,Integer>{
	
	//BoardRepository에는 아무것도없어보이지만 아니다 JpaRepository가 다들고있다
	

}

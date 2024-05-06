package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
	
	@Id //프라이머리키를 만들어주는 어노테이션
	@GeneratedValue(strategy=GenerationType.IDENTITY) //autoinrement를 사용하겠다는 어노테이션이다
	private int id; // 프리이머리키
	
	@Column(nullable = false,length = 200)
	private String content;
	
	@ManyToOne  //여러개의답변이 하나에 게시물에 있다
	@JoinColumn(name="boardid")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	@CreationTimestamp
	private Timestamp creatDate;
}

package com.cos.blog.model;


import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert //insert할때 null인 필드를 제외시켜준다
@Entity  //user클래스의 mysql테이블이 생성이된다
public class User {
	
	@Id //프라이머리키를 만들어주는 어노테이션
	@GeneratedValue(strategy=GenerationType.IDENTITY) //autoinrement를 사용하겠다는 어노테이션이다
	private int id; // 프리이머리키
	
	@Column(nullable = false,length = 30) //컬럼이 null이될수없고 길이가 30자가 넘을수 없다는 뜻이다
	private String username; //유저이름
	
	@Column(nullable = false,length = 100) //비밀번호의 길이를 많이주는이유는 암호화할거이기때문이다
	private String password; //유저비밀번호
	
	@Column(nullable = false,length = 50)
	private String email; //유저이메일
	
	@Enumerated(EnumType.STRING) //enum이 무슨데이터인지모르니 이런어노테이션을 붙여준다
	private RoleType role; //Enum인 RoleType.java에 USER와 ADMIN값을넣어 둘중하나만사용할수있게 한것이다
	
	@CreationTimestamp //현재의시간이 자동으로 입력되는 어노테이션이다
	private Timestamp createDate;

}

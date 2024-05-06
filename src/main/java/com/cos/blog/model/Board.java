package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false,length = 100)
	private String title;
	
	@Lob //대용량데이터를 사용할때 쓰는 어노테이션이다
	private String content;
	
	@ColumnDefault("0") //count는 int형이니 작음따옴표가 필요가없다
	private int count;
	
	@ManyToOne //Many=Board고 One=user라는 뜻이다 한명에유저가 여러개의 보드를 만들수있다는 뜻이다 연관관계를 만드는 어노테이션이다
 	@JoinColumn(name="userid") //피드가 만들어질때는 userid로 만들겠다는 뜻이다
	private User user; //원래 DB는 오브젝트를 저장할수없다.하지만 자바는 오브젝트를 저장할수있다
	
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER)  //onetomany어노테이션은 칼럼을 만들지않는다
	private List<Reply> reply;  //reply의 리스트를 가져오겠다는 뜻이다
	
	@CreationTimestamp  //값이 업데이트될때 자동으로 넣어주는 어노테이션이다
	private Timestamp createDate;

}

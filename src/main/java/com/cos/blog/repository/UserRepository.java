package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	//JPA Naming 쿼리
	//SELECT * FROM user WHERE username=?1 and passward=?2; 라는 쿼리는 자동적으로 생성해준다 사긴데?
	User findByUsernameAndPassword(String username,String password); //findBy다음에 대문자들이 중요~!!
	
	
//	@Query(value="SELECT * FROM user WHERE username=?1 and passward=?2;",nativeQuery=true) 
//	User login(String username,String password); //쿼리를 직접 실행시키는 쿼리이다 아무거나 사용해도된다

}

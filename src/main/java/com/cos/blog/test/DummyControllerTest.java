package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@RestController
public class DummyControllerTest {
	
	@Autowired //의존성주입(DI)해주는 어노테이션	
	private UserRepository userRepository;
	
	
	@GetMapping("/dummy/user/{id}") //{}을 사용하면 파라미터로 매핑이가능하다
	public User detail(@PathVariable int id) { //파라미터를 받을려면 @PathVariable 이라는 어노테이션이필요하다
		
		//만약 id값을 데이터베이스에서 못찾아오면 user가 null이되기때문에 return값이 문제가될것이다
		//그걸 방지하기위해 optional타입으로 가져올테니 null인지 아닌지 판단해서 return하면된다
		//findById().get()을하면 null이든말든 무조건 return한다
		User user = userRepository.findById(id);
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("id : "+user.getId());
		System.out.println("time : "+user.getCreateDate());
		System.out.println("role : "+user.getRole());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다";
	}

}

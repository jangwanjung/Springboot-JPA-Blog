package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.transaction.Transactional;


@RestController
public class DummyControllerTest {
	
	@Autowired //의존성주입(DI)해주는 어노테이션	
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		userRepository.deleteById(id);
		
		return "삭제되었습니다 id : "+id;
	}
	
	@Transactional //더티체킹=Transactional이 user값이 수정되었을때 DB에 UPDATE를하고 수정이안되었을때는 UPDATE를 안한느것을 말한다
	@PutMapping("/dummy/user/{id}")  //put요청과 get요청이 다르면 주소가같아도된다
	public User updateUser(@PathVariable int id,@RequestBody User requestUser){ //json형식으로 받을려면 바디로받아야한다
		System.out.println("id : "+id);                       //바디로받기위해서는 @RequestBody를 사용해야한다
		System.out.println("password : "+requestUser.getPassword());
		System.out.println("email : "+requestUser.getEmail());
		
		User user=userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);  //@Transactional어노테이션을 사용하면 save를 사용안해도된다 update할때 사용하자
		return user;
		
	}
	
	@GetMapping("/dummy/users")  //user의 모든값을 받는방법
	public List<User> list(){   //user의 모든값이니깐 list로 받아야한다
		return userRepository.findAll(); //restcontroller로 받을수있는이유 spring이 알아서 파일을 json형태롤 변환해준다1
		
	}
	
	@GetMapping("/dummy/user")   //pageable은 페이지를 정렬할때 사용한다 크기를2 로 id를 내림차순으로 정렬한다는뜻이다
	public List<User> pagelist(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
			Page<User> paingUser=userRepository.findAll(pageable);
			List<User>users=paingUser.getContent();
			return users; // /dummy/user/page?page=0 부터 첫번째페이지 page=1은 두번째페이지다 파라미터로 페이지를 보여줄수있다
		
	}
	
	
	@GetMapping("/dummy/user/{id}") //{}을 사용하면 파라미터로 매핑이가능하다
	public User detail(@PathVariable int id) { //파라미터를 받을려면 @PathVariable 이라는 어노테이션이필요하다
		
		//만약 id값을 데이터베이스에서 못찾아오면 user가 null이되기때문에 return값이 문제가될것이다
		//그걸 방지하기위해 optional타입으로 가져올테니 null인지 아닌지 판단해서 return하면된다
		//findById().get()을하면 null이든말든 무조건 return한다
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}

		});
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

package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); // 원문을받고
		String encPassword = encoder.encode(rawPassword); // 해쉬를 한다
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	public User 회원찾기 (String username) {
		
		User user = userRepository.findByUsername(username).orElseGet(()->{

			return new User();
		});
		return user;
		
	}

	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		// select를 해서 User오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다~!
		User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		// 회원수정 함수종료=서비스종료=트랜잭션종료=commit이 자동으로 된다
		// springboot가 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌
		// 세션등록

	}

	// 아래코드는 스프링시큐리티를 사용하지않았을때 사용하는 코드이다 전통적인 로그인방식의 코드이다
	/*
	 * @Transactional(readOnly = true) // Select 할 때 트랜잭션 시작, 서비스 종료시 트랜잭션 종료(정합성
	 * 유지) public User 로그인(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */

}

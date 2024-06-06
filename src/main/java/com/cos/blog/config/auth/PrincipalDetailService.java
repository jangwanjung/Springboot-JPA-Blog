package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	//스프링이 로그인 요청을 가로챌때 username,password 변수 2개를 가로채는데
	//password부분처리는 알아서함
	//그래서 아래코드에서 username이 DB에 있는지 확인해줌
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다:"+username);
				});
		// TODO Auto-generated method stub
		return new PrincipalDetail(principal); //이떄 시큐리티의 세션에 유저정보가 저장이된다
	}
	
}

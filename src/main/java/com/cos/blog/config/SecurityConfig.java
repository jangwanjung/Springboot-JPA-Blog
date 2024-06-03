package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration //아래코드를 bean등록하는 어노테이션이다
@EnableWebSecurity //시큐리티 필터를 등록시키는 어노테이션이다
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
//위에 세개의 어노테이션을 스프링시큐리티를 사용하는데 세트이다
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests() // 어떠한응답이있으면
				.antMatchers("/auth/**") //auth쪽으로 어떠한 응답이 들어오면
				.permitAll() //어떠한보안요구도없이 요청을 허용해준다는 뜻이다
				.anyRequest() //다른모든요청은
				.authenticated() // 인증이 되어야 요청을 허용해준다는 뜻이다
			.and() // 만약인증이안되었을때
				.formLogin()
				.loginPage("/auth/loginForm");  //이페이지로 변경된다
			
	}

}

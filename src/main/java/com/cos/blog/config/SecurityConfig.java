package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties.ConfigureAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



// 1. 어노테이션 제거
@Configuration
public class SecurityConfig { // 2. extends 제거

	
	

	// 3. principalDetailService 제거

	// 4. AuthenticationManager 메서드 생성
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean // IoC가 되요!!
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 5. 기본 패스워드 체크가 BCryptPasswordEncoder 여서 설정 필요 없음.
	

	// 6. 최신 버전(2.7)으로 시큐리티 필터 변경
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// 1. csrf 비활성화 테스트할때는 항상 비활성화를 해놓자 
		http.csrf(c -> c.disable());

		// 2. 인증 주소 설정 (WEB-INF/** 추가해줘야 함. 아니면 인증이 필요한 주소로 무한 리다이렉션 일어남)
		http.authorizeHttpRequests(authorize -> authorize //어떠한 응답이있으면
				.requestMatchers(new AntPathRequestMatcher("/"),new AntPathRequestMatcher("/WEB-INF/**"),
						new AntPathRequestMatcher("/auth/**"),new AntPathRequestMatcher("/js/**"),
						new AntPathRequestMatcher("/css/**"),new AntPathRequestMatcher("/image/**"),
						new AntPathRequestMatcher("/dummy/**")).permitAll() //해당주소로 요청이들어오면 어떠한보안요구도없이 요청해준다는뜻이다
				.anyRequest().authenticated()); //다른모든 요청은 인증이 되어야 허용해준다는뜻이다
		
		// 3. 로그인 처리 프로세스 설정
		http.formLogin(f -> f.loginPage("/auth/loginForm") //인증이필요한요청은 인증이안되었을때 이주소로 보낸다
				.loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당주소로 로그인을 가로채서 대신로그인해준다
				.defaultSuccessUrl("/") //로그인이 정상적으로 되면 해당주소로 이동한다
		);

		return http.build();
	}
}

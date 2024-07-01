package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {
	
	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	
	//auth는 인증이 안된사용자들이 출입할수있는 경로를 말하는것이다
	@GetMapping("/auth/joinForm")
	public String joinForm(){
		return "user/joinForm"; 
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm(){
		return "user/loginForm"; 
	}

	@GetMapping("/user/updateForm")
	public String updateForm(){
		return "user/updateForm"; 
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {
		
		//post요청할때 RestTemplate라이브러리를 자주쓴다
		RestTemplate rt = new RestTemplate();
		//httpheader에 값을 넣자
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		//http바디에 값을넣자
		MultiValueMap <String,String> param = new LinkedMultiValueMap<>();
		param.add("grant_type", "authorization_code");
		param.add("client_id", "d27d743084ed545af0a2180cdb8c3af7");
		param.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		param.add("code", code);
		
		//헤더와 바디를 합치자
		HttpEntity<MultiValueMap<String, String>> kakaoTokenReaquest = new HttpEntity<>(param,headers);
		
		//값을 보내자
		ResponseEntity<String> respones = rt.exchange(
				//post요청주소
				"https://kauth.kakao.com/oauth/token",
				//http메소드
				HttpMethod.POST,
				//보낼값
				kakaoTokenReaquest,
				String.class
		);
		
		//http요청받은것을 객체로 만들기위해서는 objectmapper이라는 라이브러리를 사용하자
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(respones.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		
		//post요청할때 RestTemplate라이브러리를 자주쓴다
		RestTemplate rt2 = new RestTemplate();
		//httpheader에 값을 넣자
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
	
		
		
		//헤더와 바디를 합치자
		HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
		
		//값을 보내자
		ResponseEntity<String> respones2 = rt2.exchange(
				//post요청주소
				"https://kapi.kakao.com/v2/user/me",
				//http메소드
				HttpMethod.POST,
				//보낼값
				kakaoProfileRequest2,
				String.class
		);
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(respones2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		User kakaoUser = User.builder()
				.username(kakaoProfile.getProperties().getNickname()+"_"+kakaoProfile.getId())
				.email(kakaoProfile.getId()+"_"+"kakao.com")
				.password(cosKey)
				.build();
		
		User originUser = userService.회원찾기(kakaoUser.getUsername());
		
		if (originUser.getUsername() == null ) {
			userService.회원가입(kakaoUser);
		}
		
		PrincipalDetail principalDetail = new PrincipalDetail(kakaoUser);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(principalDetail, principalDetail.getPassword(), principalDetail.getAuthorities()));
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		session.setAttribute(HttpSessionSecurityContextRepository.
				SPRING_SECURITY_CONTEXT_KEY, securityContext);
		

		return "redirect:/";
	}
	
	
}

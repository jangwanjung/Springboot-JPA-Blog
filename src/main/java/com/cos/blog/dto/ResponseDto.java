package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
	int status;    //통신상태를 확인하는 변수 HttpStatus.Ok 의 타입이 enum이라서 변수타입을 HttpStatus로 설정해야한다
	T data;
	        //제네릭이사용되면 같이 있는변수들이 저절로 리턴된다
 
}

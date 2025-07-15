package com.ikkikki.demo.domain;


import lombok.*;
import org.apache.ibatis.type.Alias;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("member")
public class Member {
	private Long no;	//회원번호
	private String id;  //아이디
	private String pw;    //비밀번호
	private String name;    //이름
	private String email;    //이메일
	private String regdate;  //작성일시
	//연산이 필요하다면 date...지만 여기서는 연산은 다 db에서 할거라서 string으로 처리
}

package com.ikkikki.demo.domain;


import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("reply")
public class Reply {
	private Long rno;			//댓글번호
	private String content;    //댓글내용
	private String id;		  //아이디
	private String regdate;  //작성일시
	private Long bno;		//게시글번호
}

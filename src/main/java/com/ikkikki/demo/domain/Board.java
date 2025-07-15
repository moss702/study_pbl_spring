package com.ikkikki.demo.domain;


import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("board")
public class Board {
	private Long bno;	//게시글번호
	private String title; //게시글 제목
	private String content;	//게시글 내용
	private String id;		//아이디
	private String regdate;		//작성일자
	private String moddate;		//수정일자
	private Integer cnt;	//count 조회수
	private Integer cno;	//category no 카테고리번호
	private int replyCnt; //게시글의 댓글개수
	private int attachCnt;
	
	// 답글기능을 위한 3개의 필드
	private Long grp;

	@Builder.Default
	private int seq = 1; // null이면 안됨. 기본값 1
	@Builder.Default
	private int depth = 1;
	
	
	public Board(Long bno, String title, String content, String id, String regdate, String moddate, Integer cnt,
			Integer cno, int replyCnt, int attachCnt) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.id = id;
		this.regdate = regdate;
		this.moddate = moddate;
		this.cnt = cnt;
		this.cno = cno;
		this.replyCnt = replyCnt;
		this.attachCnt = attachCnt;
	}

	@Builder.Default
	private List<Attach> attachs = new ArrayList<Attach>();

}

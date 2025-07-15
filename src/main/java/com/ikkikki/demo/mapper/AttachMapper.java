package com.ikkikki.demo.mapper;

import com.ikkikki.demo.domain.Attach;

import java.util.List;

public interface AttachMapper {
	void insert(Attach attach);
	List<Attach> list(Long bno); //특정 게시글의 첨부파일 
	Attach selectOne(String uuid);
//	void update(Attach attach); //첨부파일에는 수정 개념이 없음! 삭제후 재업로드
	void delete(String uuid);
	void deleteByBno(Long bno);
	
	List<Attach> selectYesterdayList();
}

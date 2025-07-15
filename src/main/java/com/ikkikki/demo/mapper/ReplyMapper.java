package com.ikkikki.demo.mapper;

import com.ikkikki.demo.domain.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {
	void insert(Reply reply);
	void update(Reply reply);
	void delete(Long rno);
	void deleteByBno(Long bno);
	
	Reply selectOne(Long rno);
	
	List<Reply> list(@Param("bno") Long bno, @Param("lastRno") Long lastRno);
	
}

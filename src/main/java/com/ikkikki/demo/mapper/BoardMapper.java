package com.ikkikki.demo.mapper;

import com.ikkikki.demo.domain.Board;
import com.ikkikki.demo.domain.dto.Criteria;

import java.util.List;

public interface BoardMapper {
	
	List<Board> list(Criteria cri);

	Board selectOne(long bno);

	void insert(Board board);
	
	void update(Board board);
	
	void delete(Long bno);	
	
	long getCount(Criteria cri);
	
	void increaseCnt(Long bno);
	
	void updateGrpMyself(Board board);
	
	void updateSeqIncrease(Board parent);
	
	void insertChild(Board board);
	
	int selectMaxSeq(Board parent);
}
package com.ikkikki.demo.service;

import com.ikkikki.demo.domain.Board;
import com.ikkikki.demo.domain.dto.Criteria;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ikkikki.demo.mapper.AttachMapper;
import com.ikkikki.demo.mapper.BoardMapper;
import com.ikkikki.demo.mapper.ReplyMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import util.MyBatisUtil;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BoardService {
  private BoardMapper mapper;
  private AttachMapper attachMapper;
  //private AttachLinkMapper attachLinkMapper;
  private ReplyMapper replyMapper;

  public List<Board> list(Criteria cri) {
    return mapper.list(cri);
  }

  public Board findBy(Long bno) {
    mapper.increaseCnt(bno);
    return mapper.selectOne(bno);
  }

  @Transactional
  public void write(Board board) {
    Long bno = board.getBno();
    if (bno == null) {   // 답글이 아님 :: 신규 글
      mapper.insert(board);
      mapper.updateGrpMyself(board);
    } else {  // 답글임
      // 1. 부모글 조회 * 부모의 파라미터 가져와서 조정하기
      Board parent = mapper.selectOne(bno);

      // 2. maxSeq 취득 * select
      int maxSeq = mapper.selectMaxSeq(parent);
      board.setSeq(maxSeq + 1); //순서 지정 * 부모보다 +1

      // 3. 해당 조건의 게시글들의 seq 밀기 = 내 위치에 작성하기 위한 update
      board.setGrp(parent.getGrp());
      board.setDepth(parent.getDepth() + 1); //깊이 지정 * 부모보다 +1
      mapper.updateSeqIncrease(board);

      // insert * 답글 등록하기
      mapper.insertChild(board);
    }

    board.getAttachs().forEach(a -> {
      a.setBno(board.getBno());
      attachMapper.insert(a);
    });
}

	public void modify(Board board) {
	        mapper.update(board);
	        // 기존 첨부파일의 메타데이터 제거
    //     attachMapper.deleteByBno(board.getBno());
	        // 새로 첨부파일 메타데이터 등록
	        board.getAttachs().forEach(a -> {
	  //     	a.setBno(board.getBno());
	        	attachMapper.insert(a);
	        });
    }

    public void remove(Long bno) {
            replyMapper.deleteByBno(bno);
            attachMapper.deleteByBno(bno);
            mapper.delete(bno);
    }

    public long getCount(Criteria cri) {
            return mapper.getCount(cri);
    }
}
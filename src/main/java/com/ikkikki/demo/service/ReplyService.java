package com.ikkikki.demo.service;

import com.ikkikki.demo.domain.Reply;
import com.ikkikki.demo.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
//import util.MyBatisUtil;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ReplyService {
  private ReplyMapper mapper;

	public Reply findBy(Long rno) {
    return mapper.selectOne(rno);
	}

  public List<Reply> list(Long bno, Long lastRno) {
    return mapper.list(bno, lastRno);
  }

	public void register(Reply Reply) {
     mapper.insert(Reply);
	}

	public void modify(Reply Reply) {
     mapper.update(Reply);
	}

	public void remove(Long rno) {
     mapper.delete(rno);
	}
	
}

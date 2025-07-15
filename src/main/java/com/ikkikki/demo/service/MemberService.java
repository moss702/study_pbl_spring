package com.ikkikki.demo.service;

import com.ikkikki.demo.domain.Member;
import com.ikkikki.demo.util.PasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ikkikki.demo.mapper.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
//import util.MyBatisUtil;
//import util.PasswordEncoder;

@Slf4j
@AllArgsConstructor
@Service
public class MemberService {
  private MemberMapper mapper;
  private PasswordEncoder passwordEncoder;

	public int register(Member member) {
    member.setPw(passwordEncoder.encode(member.getPw()));
    return mapper.insert(member);
	}

	public Member findById(String id) {
			return mapper.findById(id);
	}

	public boolean login(String id, String pw) {
		Member member = findById(id);
		if(member == null) { //없는 회원일때의 null값 처리
			return false;
		}
		return passwordEncoder.matches(pw, member.getPw());
		// (평문*입력받은값, 암호화 된거*보관하고있는거)
	}
}

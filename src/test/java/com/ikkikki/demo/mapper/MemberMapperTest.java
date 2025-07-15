package com.ikkikki.demo.mapper;

import com.ikkikki.demo.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class MemberMapperTest {
  @Autowired
  MemberMapper memberMapper;

  @Test
  public void testExist() {
    Assertions.assertNotNull(memberMapper);
    log.info("{}", memberMapper);
  }

  @Test
  public void testSelectOne() {
    Member member = memberMapper.findById("sae0");
    log.info("{}", member);
  }

}

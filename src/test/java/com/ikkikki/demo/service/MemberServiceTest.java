package com.ikkikki.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class MemberServiceTest {
  @Autowired
  private MemberService memberService;

  @Test
  public void testExist(){
    log.info("{}", memberService);
  }

  @Test
  public void testLogin(){
    Assertions.assertTrue(memberService.login("sae0", "1234"));
  }
}

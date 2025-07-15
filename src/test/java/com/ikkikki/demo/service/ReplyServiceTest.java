package com.ikkikki.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ReplyServiceTest {
  @Autowired
  private ReplyService replyService;

  @Test
  public void testExist(){
    log.info("{}", replyService);
  }

  @Test
  public void testFindBy(){
    replyService.findBy(148L);
  }
}

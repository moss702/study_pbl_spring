package com.ikkikki.demo.controller;

import com.ikkikki.demo.domain.Member;
import com.ikkikki.demo.domain.Reply;
import com.ikkikki.demo.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("reply")
@AllArgsConstructor
public class ReplyController {

  private ReplyService replyService;

  // 단일조회
  @GetMapping("{rno}")
  public Reply get(@PathVariable Long rno){
    return replyService.findBy(rno);
  }

  // 목록조회
  @GetMapping({"list/{bno}", "list/{bno}/{lastRno}"})
  public List<Reply> list(@PathVariable Long bno, @PathVariable(required = false) Long lastRno){
    return replyService.list(bno, lastRno);
  }

  // 댓글 작성
  @PostMapping("/")
  public Map<String, Object> write(@RequestBody Reply reply){
    replyService.register(reply);
    return Map.of("result", true, "reply", reply);
  }

  // 댓글 수정
  @PutMapping("/")
  public Map<String, Object> modify(@RequestBody Reply reply){
    replyService.modify(reply);
    return Map.of("result", true, "reply", reply);
  }

  // 댓글 삭제
  @DeleteMapping("{rno}")
  public ResponseEntity<Map<String, Object>> remove(@PathVariable Long rno){
    replyService.remove(rno);
    return ResponseEntity.ok().body(Map.of("result", true));
  }


}

package com.ikkikki.demo.controller;

import com.ikkikki.demo.domain.Board;
import com.ikkikki.demo.domain.Member;
import com.ikkikki.demo.domain.dto.Criteria;
import com.ikkikki.demo.domain.dto.PageDto;
import com.ikkikki.demo.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("board") // board 이하의 녀석들은 다 여기에서
@AllArgsConstructor // 생성자 주입
public class BoardController {
  private BoardService boardService;

  @GetMapping("list") // 겟매핑 : 알아서 list.jsp 찾아옴
  public void list(Criteria criteria, Model model) {
    model.addAttribute("boards", boardService.list(criteria));
    model.addAttribute("pageDto", new PageDto(criteria, boardService.getCount(criteria)));
  }

  @GetMapping({"view", "modify"})
  public void view(Model model, Long bno, @ModelAttribute("cri") Criteria cri) {
    model.addAttribute("board", boardService.findBy(bno));
  //  model.addAttribute("cri", cri); //view.jsp에서 cri라고 했으니 이름 변경
  } // 파라미터 수집 후 이름에 맞춰 바인딩

  @GetMapping("write")
  public String write(@ModelAttribute("cri") Criteria cri, @SessionAttribute(value = "member", required = false) Member member) {
    if(member == null) {
      return "redirect:/member/login";
    }
    return "board/write";
  }

  @PostMapping("write")
  public String write(Board board, Criteria cri){
    boardService.write(board);
    return "redirect:/board/list?" + cri.getQs2();
  }

  @PostMapping("modify")
  public String modify(Board board, Criteria cri){
    boardService.modify(board);
    return "redirect:/board/list?" + cri.getQs2();
  }

  @RequestMapping("remove")
  public String remove(Long bno, Criteria cri){
    boardService.remove(bno);
    return "redirect:/board/list?" + cri.getQs2();
  }


}

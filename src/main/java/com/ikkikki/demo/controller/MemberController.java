package com.ikkikki.demo.controller;

import com.ikkikki.demo.domain.Member;
import com.ikkikki.demo.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("member")
@AllArgsConstructor
public class MemberController {
  private MemberService memberService;

  @GetMapping("register")
  public void registerForm() {}

  @GetMapping("login")
  public void loginForm() { //doGet의 역할. 알아서 jsp 찾아간다.
  }

  @PostMapping("register")
  public String register(Member member) {
    memberService.register(member);
    return "redirect:/";
  }

  @PostMapping("login")
  public String login(Member member, HttpSession session, RedirectAttributes redirectAttributes) {  //로그인 성공시 보낼 페이지
    log.info("{}", member);
    if(memberService.login(member.getId(), member.getPw())){
      session.setAttribute("member", memberService.findById(member.getId()));
      return "redirect:/";
    }
    redirectAttributes.addFlashAttribute("msg", "로그인 실패");
    return "redirect:/member/login";
  }

  @RequestMapping(value = "logout", method = {RequestMethod.GET, RequestMethod.POST})
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }
}

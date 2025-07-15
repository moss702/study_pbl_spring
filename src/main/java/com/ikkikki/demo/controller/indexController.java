package com.ikkikki.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class indexController {
    @GetMapping("/")
    public String hello() {
        log.info("인덱스 컨트롤러 진입!!");

        return "index";
    }
    
}

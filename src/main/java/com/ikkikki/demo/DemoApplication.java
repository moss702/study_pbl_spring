package com.ikkikki.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@MapperScan("com.ikkikki.demo.mapper")
@SpringBootApplication
@Slf4j
public class DemoApplication {

	public static void main(String[] args) { SpringApplication.run(DemoApplication.class, args);}

}

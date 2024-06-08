package com.buffer.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloController {
  @GetMapping("/")
  public String sayHello(){
    return "Hello World aaa";
  }
}

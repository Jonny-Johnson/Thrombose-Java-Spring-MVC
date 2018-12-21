package com.john.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatExampleController {

   @GetMapping("/chat")
   public String index() {
      return "chat";
   }
}
package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MyService;

@RestController
public class MyController {
  
  @Autowired
  private MyService myService;
  
  private Logger logger = LoggerFactory.getLogger(MyController.class);

  @PostMapping("/sql")
  public Object executer(@RequestBody String body){
    if(body != null && !body.isEmpty()) {
      logger.info(body);
      Object obj = myService.executSQL(body);
      logger.info("result: {}",obj);
      return obj;
    }
    return null;
  }
}

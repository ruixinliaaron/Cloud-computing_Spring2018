//package com.csye6225.spring2018.controller;
//
//import com.csye6225.spring2018.Entity.Account;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttribute;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class IndexController {
//
//  private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
//
//  @RequestMapping("/")
//  public String index(HttpSession session, Model model) {
//    Object obj =  session.getAttribute(com.github.carter659.spring13.WebSecurityConfig.SESSION_KEY);
//    model.addAttribute("account", obj);
//    logger.info(com.github.carter659.spring13.WebSecurityConfig.SESSION_KEY);
//    return "index";
//  }
//
//
//}

//package com.csye6225.spring2018.controller;
//
//import com.csye6225.spring2018.Entity.Account;
//import com.csye6225.spring2018.Repository.Userrepository;
////import com.csye6225.spring2018.WebSecurity.WebSecurityConfig;
//import com.csye6225.spring2018.WebSecurity.WebSecurityConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class FormController {
//    @Autowired
//    private Userrepository userRepository;
//
//    @GetMapping("/login")
//    public String index(HttpSession session, Model model) {
//        Object obj =  session.getAttribute(WebSecurityConfig.SESSION_KEY);
//        model.addAttribute("account", obj);
//        System.out.println(WebSecurityConfig.SESSION_KEY);
//        return "login1";
//    }
//
//    @PostMapping("/login")
//    public String submitAccount(@ModelAttribute Account account,HttpSession session) {
////        userRepository.save(account);
//        return "result";
//    }
//}

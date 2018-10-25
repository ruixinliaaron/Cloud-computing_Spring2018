//package com.csye6225.spring2018.controller;
//
//import com.csye6225.spring2018.Entity.Account;
//import com.csye6225.spring2018.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@RequestMapping(value = "/user")
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @RequestMapping(value = "/show")
//    @ResponseBody
//    public String show(@RequestParam(value = "emailAddr") String emailAddr) {
//        Account useraccount = userService.findUserByemailAddr(emailAddr);
//        if (null != useraccount)
//            return useraccount.getId() + " & " + useraccount.getEmailAddr();
//        else return "null";
//    }
//}

package com.csye6225.spring2018.controller;

import com.csye6225.spring2018.Entity.Account;
import com.csye6225.spring2018.Repository.Userrepository;
//import com.csye6225.spring2018.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presentation")

public class UserDbController {
        @Autowired
        private Userrepository userRepository;

//        @RequestMapping(method = RequestMethod.GET)
//        public List<Account> userfind() {
//            List<Account> useraccounttList = userRepository.findAll();
//            for (int i = 0; i < useraccounttList.size(); i++) {
//                System.out.println(useraccounttList.get(i));
//            }
//            return useraccounttList;
//        }

        @RequestMapping(method = RequestMethod.GET)
        public String userdelete() throws Exception {
            Account useraccount=userRepository.selectAllByemailAddr("1");
            return useraccount.getPassword();
        }
}

//package com.csye6225.spring2018.Service;
//
//import com.csye6225.spring2018.Entity.Account;
//import com.csye6225.spring2018.Repository.Userrepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//    @Autowired
//    private Userrepository userRepository;
//    public Account findUserByemailAddr(String emailAddr) {
//        Account useraccount = null;
//        try {
//            useraccount = userRepository.findByUseremailAddr(emailAddr);
//        } catch (Exception e) {
//        }
//        return useraccount;
//    }
//}

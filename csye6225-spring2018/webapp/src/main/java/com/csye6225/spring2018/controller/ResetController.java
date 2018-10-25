package com.csye6225.spring2018.controller;

import com.csye6225.spring2018.Repository.Userrepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
@Controller
public class ResetController {
    @Autowired
    private Userrepository userRepository;

    private final static Logger logger = LoggerFactory.getLogger(ResetController.class);

    @PostMapping("/resetPost")
    public String resetPost(String oldpassword, String newpassword, String verify, Model model, HttpSession session) {

        if(userRepository.selectAllByemailAddr(session.getAttribute("name").toString())!=null&&oldpassword!=null&&newpassword!=null&&verify!=null){
            if(!verify.equals(newpassword)){
                model.addAttribute("errmessage","The two passwords you typed do not match!");
            }
            else if(!BCrypt.checkpw(oldpassword,userRepository.selectAllByemailAddr(session.getAttribute("name").toString()).getPassword())){
                model.addAttribute("errmessage","Wrong password!");
            }
            else {
                userRepository.updatePasswordByemailAddr(session.getAttribute("name").toString(),(String)BCrypt.hashpw(newpassword, BCrypt.gensalt()));
                model.addAttribute("errmessage","You successfully changed your password!");
            }
        }
        else{
            model.addAttribute("errmessage","These are required inputs!");
        }
        return "reset";
    }
}

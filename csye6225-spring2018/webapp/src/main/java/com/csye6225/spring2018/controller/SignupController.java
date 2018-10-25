package com.csye6225.spring2018.controller;
import com.csye6225.spring2018.Entity.Account;
import com.csye6225.spring2018.Repository.Userrepository;
import com.csye6225.spring2018.WebSecurity.Sendmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class SignupController {
    @Autowired
    private Userrepository userRepository;

    @PostMapping("/signupPost")
    public String signupPost(String account, String password,String imgAddr,Model model,HttpSession session) {
        if (session.getAttribute("name") == null) {
            if (userRepository.selectAllByemailAddr(account) == null) {
                Account useraccount = new Account();
                useraccount.setEmailAddr(account);
                useraccount.setPassword((String)BCrypt.hashpw(password, BCrypt.gensalt()));
                useraccount.setImgAddr(imgAddr);
                useraccount.setIntro("");
                userRepository.save(useraccount);
                model.addAttribute("signupresult", true);
                Sendmail sendmail=new Sendmail();
                sendmail.send(account,account.substring(0,account.toString().indexOf('@')));
                return "login";
            }
            else{
                model.addAttribute("signupresult", false);
                model.addAttribute("errmessage","This user has already existed");
            }
            return "signup";
        }
        else{
            model.addAttribute("errmessage","You have already logged in");
            return "signup";
        }
    }
}

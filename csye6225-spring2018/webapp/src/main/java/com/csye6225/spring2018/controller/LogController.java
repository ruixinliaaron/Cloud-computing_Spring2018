package com.csye6225.spring2018.controller;

import com.amazonaws.services.s3.model.S3Object;
import com.csye6225.spring2018.Entity.Account;
import com.csye6225.spring2018.Repository.Userrepository;
import com.csye6225.spring2018.S3uploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LogController {
    @Autowired
    private Userrepository userRepository;
    @Autowired
    private Environment env;

    @PostMapping("/loginPost")
    public String loginPost(String account, String password, HttpSession session, Model model) {
        if(env.getProperty("profile").equals("aws_environment")){
        Account useraccount = userRepository.selectAllByemailAddr(account);

        if (useraccount==null){
            model.addAttribute("errmessage","The user does not exist!");
            return "login";
        }

        if (!BCrypt.checkpw(password,useraccount.getPassword())) {
            model.addAttribute("errmessage","Password does not match!");
            return "login";
        }

        else {
            session.setAttribute("name", account);
            String keyname = "pictures/" + session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')).replace(".", "");
            if (!S3uploadUtil.checkObject(keyname)) {
                session.setAttribute("imgAddr", "");
            } else {
<<<<<<< HEAD
                String imgurl = S3uploadUtil.getpublicurl(keyname,"ruixinli");
=======
                String imgurl = S3uploadUtil.getpublicurl(keyname,"pokemonball");
>>>>>>> assignment09
                session.setAttribute("imgAddr", imgurl);
            }
            model.addAttribute("name", session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')));
            if (session.getAttribute("imgAddr").equals(null) || session.getAttribute("imgAddr").equals("")) {
                model.addAttribute("imgAddr", "https://s3.amazonaws.com/pokemonball/pictures/primitive.JPG");
            } else {
                model.addAttribute("imgAddr", session.getAttribute("imgAddr"));
            }
            model.addAttribute("flag", "true");
        }
        }
        else {
            Account useraccount = userRepository.selectAllByemailAddr(account);

            if (useraccount==null){
                model.addAttribute("errmessage","The user does not exist!");
                return "login";
            }

            if (!BCrypt.checkpw(password,useraccount.getPassword())) {
                model.addAttribute("errmessage","Password does not match!");
                return "login";
            }

            else {
                session.setAttribute("name", account);
                session.setAttribute("imgAddr", useraccount.getImgAddr().toString());
                model.addAttribute("name",session.getAttribute("name").toString().substring(0,session.getAttribute("name").toString().indexOf('@')));
                if(session.getAttribute("imgAddr").equals(null)||session.getAttribute("imgAddr").equals("")){
                    model.addAttribute("imgAddr", "images/default.jpg");
                }
                else{ model.addAttribute("imgAddr",session.getAttribute("imgAddr"));}
                model.addAttribute("flag","true");
            }
        }
        return "index";
    }
}

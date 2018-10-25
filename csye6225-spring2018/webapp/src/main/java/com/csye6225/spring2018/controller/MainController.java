package com.csye6225.spring2018.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

<<<<<<< HEAD
=======
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

>>>>>>> assignment09
import com.csye6225.spring2018.Entity.Account;
import com.csye6225.spring2018.Repository.Userrepository;
import com.csye6225.spring2018.S3uploadUtil;
import com.csye6225.spring2018.WebSecurity.Sendmail;
import com.csye6225.spring2018.WebSecurity.WebSecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
=======
import com.amazonaws.auth.BasicAWSCredentials;
import com.csye6225.spring2018.Entity.Account;
import com.csye6225.spring2018.Repository.Userrepository;
import com.csye6225.spring2018.S3uploadUtil;
import com.csye6225.spring2018.WebSecurity.Sendmail;
import com.csye6225.spring2018.WebSecurity.WebSecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;

>>>>>>> assignment09

@Controller
public class MainController {
    @Autowired
    private Userrepository userRepository;

    private final static Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private Environment env;
    @RequestMapping("/test")
    public String test() {
        System.out.println(env.getProperty("profile"));
        return "index";
    }

       @RequestMapping("/")
    public String index(HttpSession session, Model model) {
        if (session.getAttribute("name") == null) {
            model.addAttribute("flag", "false");
        }
        else {
            model.addAttribute("flag", "true");
            model.addAttribute("name", session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')));
            if (session.getAttribute("imgAddr").equals(null) || session.getAttribute("imgAddr").equals("")) {
                model.addAttribute("imgAddr", "https://s3.amazonaws.com/pokemonball/pictures/primitive.JPG");
            }
            else model.addAttribute("imgAddr", session.getAttribute("imgAddr"));
        }

        return "index";
}


    @GetMapping("/login")
    public String login(HttpSession session, Model model) {
        if (session.getAttribute("name") != null) {
            model.addAttribute("name", session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')));
            model.addAttribute("imgAddr", session.getAttribute("imgAddr"));
            model.addAttribute("flag", "true");
            return "index";
        } else return "login";
    }


    @GetMapping("/signup")
    public String signup(HttpSession session, Model model) {
        if (session.getAttribute("name") != null) {
            model.addAttribute("name", session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')));
            model.addAttribute("imgAddr", session.getAttribute("imgAddr"));
            model.addAttribute("flag", "true");
            return "index";
        } else return "signup";
    }

    @RequestMapping("/aboutme")
    public String aboutme(HttpSession session, Model model,String username,String intro) {
        Account useraccount = userRepository.selectAllByemailAddr(username+"@husky.neu.edu");
        if (useraccount==null){
            model.addAttribute("errmessage","The user does not exist!");
            return "index";
        }
        else{
                if (intro==(null)) {
                    try {
                        if ((session.getAttribute("name")!=(null))&&(username + "@husky.neu.edu").equals(session.getAttribute("name").toString())) {
                            model.addAttribute("introflag", "true");
                        }
                        else model.addAttribute("introflag", "false");
                    }
                    catch(NullPointerException e){
                        e.printStackTrace();
                    }
                    finally {
                        if(useraccount.getIntro().equals(null)||useraccount.getIntro().equals("")){System.out.println(1);session.setAttribute("intro", "This man have not set your introduction yet~~");}
                        else{System.out.println(2);session.setAttribute("intro", useraccount.getIntro().toString());}
                        model.addAttribute("username", username);
                        model.addAttribute("introfromdb", session.getAttribute("intro"));
                        return "aboutme";
                    }
                }
                else if(intro.length()>140){
                    model.addAttribute("introflag", "true");
                    model.addAttribute("errmessage","Sorry, you can not insert more than 140 characters");
                    return "aboutme";
                }
                else {
                    intro.replaceAll("&lt","<").replaceAll("&gt",">");
                    useraccount.setIntro(intro);
                    userRepository.save(useraccount);
                    if(useraccount.getIntro().equals("")||useraccount.getIntro().equals(null)){System.out.println(3);session.setAttribute("intro", "This man have not set your introduction yet~~");}
                    else{System.out.println(4);session.setAttribute("intro", useraccount.getIntro().toString());}
                    model.addAttribute("introflag", "true");
                    model.addAttribute("username", username);
                    model.addAttribute("introfromdb", session.getAttribute("intro"));
                    return "aboutme";
                }
        }
    }
//    @PostMapping("/loginPost")
//    public String loginPost(String account, String password, HttpSession session,Model model) {
//        Map<String, Object> map = new HashMap<>();
//        if (!"123456".equals(password)) {
//            map.put("success", false);
//            map.put("message", "wrong password");
//            return "err";
//        }
//
//        session.setAttribute("name", account);
//        model.addAttribute("name", session.getAttribute("name"));
//        map.put("success", true);
//        map.put("message", "log in successfully");
//        return "index";
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("name");
        return "redirect:/";
    }

<<<<<<< HEAD
    @GetMapping("/resetpassword")
    public String resetpassword(HttpSession session, Model model) {
        return "reset";
=======
@RequestMapping("/resetpassword")
    public String resetpassword(HttpSession session, Model model) {

        String topicArn = "arn:aws:sns:us-east-1:581354222471:resetPassword";
        //create a new SNS client and set endpoint
        String ACCESS_KEY = "AKIAIJKUJYXLPKXFFT7A";
        String SECRET_KEY = "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";
        BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY);
        AmazonSNSClient snsClient = new AmazonSNSClient(credentials);
        snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));
        //publish to an SNS topic
        String msg = session.getAttribute("name")+"";
        PublishRequest publishRequest = new PublishRequest(topicArn, msg);
        PublishResult publishResult = snsClient.publish(publishRequest);
        //print MessageId of message published to SNS topic
        System.out.println("MessageId - " + publishResult.getMessageId());
        if(env.getProperty("profile").equals("aws_environment")) {
            if (session.getAttribute("name") == null) {
                model.addAttribute("flag", "false");
            } else {
                model.addAttribute("flag", "true");
                model.addAttribute("name", session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')));
                if (session.getAttribute("imgAddr").equals(null) || session.getAttribute("imgAddr").equals("")) {
                    model.addAttribute("imgAddr", "https://s3.amazonaws.com/pokemonball/pictures/primitive.JPG");
                } else model.addAttribute("imgAddr", session.getAttribute("imgAddr"));
            }
        }
        else
        {
            if (session.getAttribute("name") == null) {
                model.addAttribute("flag", "false");
            } else {
                model.addAttribute("flag", "true");
                model.addAttribute("name", session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')));
                if (session.getAttribute("imgAddr").equals(null) || session.getAttribute("imgAddr").equals("")) {
                    model.addAttribute("imgAddr", "images/default.jpg");
                } else model.addAttribute("imgAddr", session.getAttribute("imgAddr"));
            }
        }
        return "index";
>>>>>>> assignment09
    }
}

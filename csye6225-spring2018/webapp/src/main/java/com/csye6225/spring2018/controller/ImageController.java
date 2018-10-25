package com.csye6225.spring2018.controller;

import com.csye6225.spring2018.Entity.Account;
import com.csye6225.spring2018.FileUtil;
import com.csye6225.spring2018.Repository.Userrepository;
import com.csye6225.spring2018.S3uploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;


@Controller
public class ImageController {
    @Autowired
    private Environment env;
    @Autowired
    private Userrepository userRepository;

    @RequestMapping(value="/uploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        return "uploadimg";
    }


    @RequestMapping(value="/imgupload", method = RequestMethod.POST)
    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request,HttpSession session,Model model) {

//            String profix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//            String fileName = session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')).replace(".", "") + "_headshot";
//            String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
            String keyname = "pictures/" + session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')).replace(".", "");

            try {
                File cvtfile = S3uploadUtil.convertMultiPartToFile(file,request);
                cvtfile.setWritable(true,false);
                cvtfile.setExecutable(true,false);
<<<<<<< HEAD
                S3uploadUtil.upload(keyname, cvtfile,"web-app.csye6225-spring2018-liruix.me");
                S3uploadUtil.upload(keyname, cvtfile,"ruixinli");
                String imgurl = S3uploadUtil.getpublicurl(keyname,"ruixinli");
=======
                S3uploadUtil.upload(keyname, cvtfile,"web-app.csye6225-spring2018-yaojiaw.me");
                S3uploadUtil.upload(keyname, cvtfile,"pokemonball");
                String imgurl = S3uploadUtil.getpublicurl(keyname,"pokemonball");
>>>>>>> assignment09
                session.setAttribute("imgAddr", imgurl);
                if (session.getAttribute("name") == null) {
                    model.addAttribute("flag", "false");
                } else {
                    model.addAttribute("flag", "true");
                    model.addAttribute("name", session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')));
                    if (session.getAttribute("imgAddr").equals(null) || session.getAttribute("imgAddr").equals("")) {
                        model.addAttribute("imgAddr", "https://s3.amazonaws.com/pokemonball/pictures/primitive.JPG");
                    }
                    else model.addAttribute("imgAddr", session.getAttribute("imgAddr"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return "index";
    }

    @RequestMapping(value="/deleteimg", method = RequestMethod.GET)
    public String deleteimg(HttpServletRequest request,HttpSession session,Model model){
            String keyname = "pictures/" + session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')).replace(".", "");
<<<<<<< HEAD
            S3uploadUtil.deleteObject(keyname,"web-app.csye6225-spring2018-liruix.me");
            S3uploadUtil.deleteObject(keyname,"ruixinli");
=======
            S3uploadUtil.deleteObject(keyname,"web-app.csye6225-spring2018-yaojiaw.me");
            S3uploadUtil.deleteObject(keyname,"pokemonball");
>>>>>>> assignment09
            session.setAttribute("imgAddr", "");
            if (session.getAttribute("name") == null) {
                model.addAttribute("flag", "false");
            } else {
                model.addAttribute("flag", "true");
                model.addAttribute("name", session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')));
                if (session.getAttribute("imgAddr").equals(null) || session.getAttribute("imgAddr").equals("")) {
                    model.addAttribute("imgAddr", "https://s3.amazonaws.com/pokemonball/pictures/primitive.JPG");
                } else model.addAttribute("imgAddr", session.getAttribute("imgAddr"));
            }
        
        return "index";
    }
}

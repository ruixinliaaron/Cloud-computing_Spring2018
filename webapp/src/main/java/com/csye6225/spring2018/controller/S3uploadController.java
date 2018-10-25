package com.csye6225.spring2018.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.csye6225.spring2018.S3uploadUtil;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class S3uploadController {

    @RequestMapping("/s3upload")
    public String s3upload() {
        return "s3upload";
    }

    @RequestMapping("/s3uploadpost")
    public String s3uploadpost( @RequestParam("file") MultipartFile file,HttpServletRequest request,String bucket_name,String key_name) {
        S3uploadUtil.createBucket(bucket_name);
        String fileName = file.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
        try {
            File cvtfile = S3uploadUtil.convertMultiPartToFile(file,request);
            S3uploadUtil.upload(key_name,cvtfile,"pokemonball");
        }
        catch (IOException e){e.printStackTrace();}
        return "index";
    }
}

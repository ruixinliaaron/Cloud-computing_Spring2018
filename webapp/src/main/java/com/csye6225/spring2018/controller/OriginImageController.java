//package com.csye6225.spring2018.controller;
//
//import com.csye6225.spring2018.Entity.Account;
//import com.csye6225.spring2018.FileUtil;
//import com.csye6225.spring2018.Repository.Userrepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//
//@Controller
//public class OriginImageController {
//    @Autowired
//    private Userrepository userRepository;
//
////    @RequestMapping("/imageupload")
////    public String imageupload(String account,String imgAddr,HttpSession session){
////        Account useraccount = userRepository.selectAllByemailAddr(account);
////        useraccount.setImgAddr(imgAddr);
////        session.setAttribute("imgAddr", useraccount.getImgAddr().toString());
////        return "index";
////    }
//
//    @RequestMapping(value="/uploadimg", method = RequestMethod.GET)
//    public String goUploadImg() {
//        return "uploadimg";
//    }
//
//
//    @RequestMapping(value="/imgupload", method = RequestMethod.POST)
//    public String uploadImg(String account,@RequestParam("file") MultipartFile file,
//                            HttpServletRequest request,HttpSession session,Model model) {
//
//        String contentType = file.getContentType();
//        String fileName = file.getOriginalFilename();
//        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
//
//        try {
//            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        Account useraccount = userRepository.selectAllByemailAddr(session.getAttribute("name").toString());
//        useraccount.setImgAddr("images/"+fileName);
//        userRepository.save(useraccount);
//        session.setAttribute("imgAddr", useraccount.getImgAddr().toString());
//
//        if (session.getAttribute("name") == null) {
//            model.addAttribute("flag", "false");
//        }
//        else {
//            model.addAttribute("flag", "true");
//            model.addAttribute("name", session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')));
//            if(session.getAttribute("imgAddr").equals(null)||session.getAttribute("imgAddr").equals("")){
//                model.addAttribute("imgAddr", "images/default.jpg");
//            }
//            else model.addAttribute("imgAddr", session.getAttribute("imgAddr"));
//        }
//        return "index";
//    }
//
//    @RequestMapping(value="/deleteimg", method = RequestMethod.GET)
//    public String deleteimg(String account,HttpServletRequest request,HttpSession session,Model model){
//        Account useraccount = userRepository.selectAllByemailAddr(session.getAttribute("name").toString());
//        useraccount.setImgAddr("");
//        userRepository.save(useraccount);
//        session.setAttribute(   "imgAddr", useraccount.getImgAddr().toString());
//
//        if (session.getAttribute("name") == null) {
//            model.addAttribute("flag", "false");
//        }
//        else {
//            model.addAttribute("flag", "true");
//            model.addAttribute("name", session.getAttribute("name").toString().substring(0, session.getAttribute("name").toString().indexOf('@')));
//            if(session.getAttribute("imgAddr").equals(null)||session.getAttribute("imgAddr").equals("")){
//                model.addAttribute("imgAddr", "images/default.jpg");
//            }
//            else model.addAttribute("imgAddr", session.getAttribute("imgAddr"));
//        }
//        return "index";
//    }
//}

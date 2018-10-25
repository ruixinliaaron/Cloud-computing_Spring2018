package com.csye6225.spring2018.WebSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Sendmail {

    public void send(String receivermail,String name)
    {
        try{
            Properties props=new Properties();

            props.put("mail.smtp.host", "smtp.163.com");
            props.put("mail.smtp.auth", true);

            Session mailConnection=Session.getInstance(props,null);
            Message msg=new MimeMessage(mailConnection);

            Address sender=new InternetAddress("18910931590@163.com");
            Address receiver=new InternetAddress(receivermail);

            msg.setFrom(sender);
            msg.setRecipient(Message.RecipientType.TO, receiver);
            msg.setSubject("You have successfully sign up");
            msg.setText("Hi,"+name+":\n box is a shabi");
            //msg.setContent("Hello", "text/plain");


            Transport trans=mailConnection.getTransport("smtp");
            String username="18910931590@163.com";
            String pw="15035040929lrx";
            trans.connect("smtp.163.com", username,  pw);
            trans.sendMessage(msg, msg.getAllRecipients());
            trans.close();
        }catch(Exception e)
        {
            System.err.println(e);
        }
        finally{
            //System.exit(0);
        }

    }
}


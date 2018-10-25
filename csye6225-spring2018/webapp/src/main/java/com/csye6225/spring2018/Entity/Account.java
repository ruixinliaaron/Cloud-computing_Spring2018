package com.csye6225.spring2018.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="emailAddr")
    private String emailAddr;

    @Column(name="password")
    private String password;

    @Column(name="imgAddr")
    private String imgAddr;

    @NotNull
    @Column(name="intro")
    private String intro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgAddr(){return imgAddr;}

    public void setImgAddr(String imgAddr){ this.imgAddr=imgAddr;}

    public String getIntro(){return intro;}

    public void setIntro(String intro){ this.intro=intro;}


}

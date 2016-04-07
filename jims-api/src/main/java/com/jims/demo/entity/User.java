package com.jims.demo.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by JIMS on 2016/4/5.
 */
@XmlRootElement
public class User implements Serializable {

    private String userName ;
    private String password ;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

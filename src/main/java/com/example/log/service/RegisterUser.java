package com.example.log.service;

import com.example.log.model.UserDetails;
import com.example.log.repository.LoginOrRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class RegisterUser {

    @Autowired
    LoginOrRegister loginOrRegister;
    Timestamp current;

    public boolean register(UserDetails user){

        Date now = new Date();
        System.out.println("Inside register(), values = "+user.toString());
        Timestamp currentlogintime = new Timestamp(now.getTime());
        System.out.println("Inside register(), values = "+user.toString());
        user.setCurrentlogintime(currentlogintime);
        //user.setLastlogintime(Timestamp.valueOf(current.toLocalDateTime()));
        System.out.println("New user added = "+user.toString());
        UserDetails storeUser = loginOrRegister.save(user);
        return true;
    }
}

package com.example.log.service;

import com.example.log.model.UserDetails;
import com.example.log.repository.LoginOrRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class CheckUser {

    @Autowired
    LoginOrRegister loginOrRegister;

    UserDetails userDetailsFromDb;
    Timestamp current;

    public boolean checkCred(UserDetails user) {

        System.out.println("inside check cred()" + user.getUsername()+user.toString());
        boolean isPresent = loginOrRegister.existsByUsername(user.getUsername());

        if(isPresent) {
            Date now = new Date();
            Timestamp currentlogintime = new Timestamp(now.getTime());
            userDetailsFromDb = loginOrRegister.findByUsername(user.getUsername());
            Timestamp t = userDetailsFromDb.getCurrentlogintime();
            userDetailsFromDb.setCurrentlogintime(currentlogintime);

                String password = userDetailsFromDb.getPassword();
                System.out.println("jjj" + userDetailsFromDb.toString());
                if (password.equals(user.getPassword())) {
                    System.out.println("creds are correct");

                    loginOrRegister.save(userDetailsFromDb);

                    userDetailsFromDb.setLastlogintime(t);
                    return true;
                } else
                    System.out.println("creds are incorrect");


        }
        else{
            System.out.print("This user not exist");
        }


        return false;
    }
}

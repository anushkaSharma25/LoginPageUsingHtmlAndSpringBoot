package com.example.log.controller;

import com.example.log.model.UserDetails;
import com.example.log.repository.LoginOrRegister;
import com.example.log.service.CheckUser;
import com.example.log.service.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Component
public class MyController {

    @Autowired
    LoginOrRegister loginOrRegister;
    @Autowired
    CheckUser checkUser;
    @Autowired
    RegisterUser registerUser;
    boolean result;
    Timestamp current;



    UserDetails currentuser= new UserDetails();


    @RequestMapping("/index1")
    public void hello(){    }

    @RequestMapping(value="/register")
    public ModelAndView registerUser(UserDetails userCred){

        Date now = new Date();
        System.out.println("New user added above if = "+userCred.toString());
        Timestamp timestamp = new Timestamp(now.getTime());

        if (!(userCred.getUsername() == null)){
            System.out.println("inside else");
            if ((userCred.getPassword()!=null)) {
                this.currentuser = userCred;
                System.out.println("New user registered = " + this.currentuser.toString());
                userCred.setCurrentlogintime(timestamp);
                userCred.setLastlogintime(timestamp);
                System.out.println("inside inner if = "+userCred.toString());
                registerUser.register(userCred);
                return new ModelAndView("/newregistersuccess");
            }
            else {
                System.out.println("Empty password");
                return new ModelAndView("/fail");
            }
        }
        return new ModelAndView("/register");

    }

    @RequestMapping(value = "/login")
    public ModelAndView login(UserDetails user, HttpServletRequest request){

        System.out.println(user.toString()+" inside login");
        List<String> users = (List<String>) request.getSession().getAttribute("USER_SESSION");
        this.currentuser = loginOrRegister.findByUsername(user.getUsername());
        //check if notes is present in session or not
        if (users == null) {
            users = new ArrayList<>();
            // if user object is not present in session, set notes in the request session
            request.getSession().setAttribute("users_SESSION", users);
        }
        users.add(user.getUsername());
        request.getSession().setAttribute("users_SESSION", users);

        System.out.println(user.getUsername()+" "+user.getPassword());

        if(user.getUsername() != null) {
            result = checkUser.checkCred(user);
            if(result == true) {
                this.currentuser = loginOrRegister.findByUsername(user.getUsername());
                HttpSession session = request.getSession();
                ModelAndView m = new ModelAndView();
                m.addObject("username",this.currentuser.getUsername());
                m.addObject("password",this.currentuser.getPassword());
                m.addObject("currentlogintime",this.currentuser.getCurrentlogintime());
                m.addObject("lastlogintime",this.currentuser.getLastlogintime());
                m.setViewName("/success");
                System.out.println(this.currentuser.toString()+" big");
                System.out.println("Values"+m.getModel());
                return m;
            }

            else {
                System.out.println("empty user");
                return new ModelAndView("/fail");

            }
        }

        else {
            return new ModelAndView("login");
        }

    }

    @RequestMapping(value = "/success")
    public void success(){}

    @RequestMapping(value = "/logout")
    public void logout(){}

    @RequestMapping(value = "/newregistersuccess")
    public void newregistersuccess(){}

    @RequestMapping(value = "/fail")
    public void fail(){}

    @RequestMapping(value = "/succ")
    public void succ(){}

}

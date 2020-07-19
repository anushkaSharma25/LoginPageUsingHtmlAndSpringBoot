package com.example.log.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "userdetails")
public class UserDetails {

    @Id
    @NotBlank(message = "Name is mandatory")
    String username;
    @NotBlank(message = "password is mandatory")
    String password;
    Timestamp currentlogintime;
    Timestamp lastlogintime;

    public UserDetails(){}

    public UserDetails(@NotBlank(message = "Name is mandatory") String username, @NotBlank(message = "password is mandatory") String password, Timestamp currentlogintime, Timestamp lastlogintime) {
        this.username = username;
        this.password = password;
        this.currentlogintime = currentlogintime;
        this.lastlogintime = lastlogintime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCurrentlogintime() {
        return currentlogintime;
    }

    public void setCurrentlogintime(Timestamp currentlogintime) {
        this.currentlogintime = currentlogintime;
    }

    public Timestamp getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Timestamp lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", currentlogintime=" + currentlogintime +
                ", lastlogintime=" + lastlogintime +
                '}';
    }
}

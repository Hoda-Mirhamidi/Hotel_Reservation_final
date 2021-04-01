package com.hotel.Hotel_Reservation_final.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class User {

    @Id
    private int user_id;
    @Column @NotNull
    private String fname;
    @Column @NotNull
    private String lname;
    @Column @NotNull
    private String password;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int user_id, String fname, String lname, String password) {
        this.user_id = user_id;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
    }
}

package com.example.librarysb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_login")
public class UserLogin {
    @Id
    private String userid;
    private String userpassword;

    private String userrole;
    private Integer borrownum;

    // Getters and Setters
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUserrole(){return userrole;}

    public void setUserrole(String userrole){this.userrole=userrole;}

    public Integer getBorrownum() {
        return borrownum;
    }

    public void setBorrownum(Integer borrownum) {
        this.borrownum = borrownum;
    }
}
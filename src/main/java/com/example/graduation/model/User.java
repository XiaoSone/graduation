package com.example.graduation.model;

import lombok.Data;

@Data
public class User {
    //用户id
    private String userId;
    //用户名
    private String userName;
    //用户账号
    private String userAccount;
    //密码
    private String userPassword;
    //电话
    private String userTel;
    //性别
    private Integer userGender;
    private String userPortrait;
    //用户角色
    private Integer userRoles;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public Integer getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Integer userRoles) {
        this.userRoles = userRoles;
    }
}

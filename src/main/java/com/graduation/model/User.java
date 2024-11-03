package com.graduation.model;

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
}

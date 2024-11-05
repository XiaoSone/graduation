package com.graduation.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {
    //管理员id
    private String adminId;
    //用户id
    private String userId;
}

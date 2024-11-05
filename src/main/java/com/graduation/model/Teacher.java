package com.graduation.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Teacher implements Serializable {
    //教师id
    private String teacherId;
    //教师描述
    private String teacherDescribe;
    //教师项目数
    private String teacherProjectNum;
    //用户id
    private String userId;
    //部门
    private Integer deptId;
    //职称
    private String zhicheng;

    private User user;

    private Integer noChooseProject;

    private List<Project> projectList;

    private Dept dept;
}

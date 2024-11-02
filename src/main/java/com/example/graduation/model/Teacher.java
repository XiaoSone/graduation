package com.example.graduation.model;

public class Teacher {
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

    //get and set
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherDescribe() {
        return teacherDescribe;
    }

    public void setTeacherDescribe(String teacherDescribe) {
        this.teacherDescribe = teacherDescribe;
    }

    public String getTeacherProjectNum() {
        return teacherProjectNum;
    }

    public void setTeacherProjectNum(String teacherProjectNum) {
        this.teacherProjectNum = teacherProjectNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getZhicheng() {
        return zhicheng;
    }

    public void setZhicheng(String zhicheng) {
        this.zhicheng = zhicheng;
    }
}

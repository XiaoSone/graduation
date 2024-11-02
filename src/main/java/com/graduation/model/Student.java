package com.graduation.model;

import lombok.Data;

@Data
public class Student {
    //学生id
    private String studentId;
    //项目数
    private Integer projectNum;
    //
    private Integer goodBoy;
    //项目id
    private Integer projectId;
    //导师id
    private String teacherId;
    //用户id
    private String userId;
    //班级id
    private Integer classId;
    //学生分数
    private Integer studentScore;
    //导师评价
    private String teacherEvaluate;
    //验收团队id
    private String yansouTeamId;

    private User user;
    private Project project;
    private ClassInfo classInfo;
    private Teacher teacher;
    private Ktbg ktbg;
    private Zqjc zqjc;
    private Mdb mdb;
    private Lunwen lunwen;
    private DaBian daBian;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(Integer projectNum) {
        this.projectNum = projectNum;
    }

    public Integer getGoodBoy() {
        return goodBoy;
    }

    public void setGoodBoy(Integer goodBoy) {
        this.goodBoy = goodBoy;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(Integer studentScore) {
        this.studentScore = studentScore;
    }

    public String getTeacherEvaluate() {
        return teacherEvaluate;
    }

    public void setTeacherEvaluate(String teacherEvaluate) {
        this.teacherEvaluate = teacherEvaluate;
    }

    public String getYansouTeamId() {
        return yansouTeamId;
    }

    public void setYansouTeamId(String yansouTeamId) {
        this.yansouTeamId = yansouTeamId;
    }
}

package com.graduation.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class YansouTeam implements Serializable{

	private Integer yansouTeamId;

    private String yansouTeamName;
    
    //级联属性
    private List<com.graduation.model.YansouTeacher> yansouTeachers;
    

	public List<com.graduation.model.YansouTeacher> getYansouTeachers() {
		return yansouTeachers;
	}

	public void setYansouTeachers(List<com.graduation.model.YansouTeacher> yansouTeachers) {
		this.yansouTeachers = yansouTeachers;
	}

	public Integer getYansouTeamId() {
        return yansouTeamId;
    }

    public void setYansouTeamId(Integer yansouTeamId) {
        this.yansouTeamId = yansouTeamId;
    }

    public String getYansouTeamName() {
        return yansouTeamName;
    }

    public void setYansouTeamName(String yansouTeamName) {
        this.yansouTeamName = yansouTeamName == null ? null : yansouTeamName.trim();
    }
}
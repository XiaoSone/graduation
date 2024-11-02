package com.graduation.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClassInfo implements Serializable{

	private Integer classId;

    private String className;

    private Integer gradeId;

    private Integer subjectId;
    
    private com.graduation.model.Grade grade;
    
    private com.graduation.model.Subject subject;
    
    public com.graduation.model.Grade getGrade() {
		return grade;
	}

	public void setGrade(com.graduation.model.Grade grade) {
		this.grade = grade;
	}

	public com.graduation.model.Subject getSubject() {
		return subject;
	}

	public void setSubject(com.graduation.model.Subject subject) {
		this.subject = subject;
	}

	public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
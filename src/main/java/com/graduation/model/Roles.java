package com.graduation.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Roles implements Serializable{

	private Integer rolesId;

    private String rolesName;

    public Integer getRolesId() {
        return rolesId;
    }

    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName == null ? null : rolesName.trim();
    }
}
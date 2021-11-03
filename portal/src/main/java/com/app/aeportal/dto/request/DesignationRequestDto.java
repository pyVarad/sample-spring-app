package com.app.aeportal.dto.request;


import com.app.aeportal.domain.Designation;

import javax.validation.constraints.NotBlank;

public class DesignationRequestDto {

    @NotBlank(message = "Designation parameter is a mandatory.")
    private String designation;

    @NotBlank(message = "Grade parameter is a mandatory.")
    private Integer grade;

    @NotBlank(message = "userRank parameter is a mandatory.")
    private Integer userRank;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getUserRank() {
        return userRank;
    }

    public void setUserRank(Integer userRank) {
        this.userRank = userRank;
    }

    public Designation toDesignation() {
        return new Designation(
                designation,
                grade,
                userRank
        );
    }
}

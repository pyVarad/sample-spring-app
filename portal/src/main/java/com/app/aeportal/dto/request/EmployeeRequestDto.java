package com.app.aeportal.dto.request;

import com.app.aeportal.constant.DomainConstant.Gender;
import com.app.aeportal.constant.DomainConstant.Segment;
import com.app.aeportal.domain.Employee;

import javax.validation.constraints.NotBlank;
import java.util.List;


public class EmployeeRequestDto {

    @NotBlank(message = "First name parameter is mandatory for the request.")
    private String firstName;

    @NotBlank(message = "Last name parameter is mandatory for the request.")
    private String lastName;

    @NotBlank(message = "#GPN ID parameter is mandatory for the request.")
    private String employeeId;

    @NotBlank(message = "Email parameter is mandatory for the request.")
    private String email;

    @NotBlank(message = "isActive name parameter is mandatory for the request.")
    private boolean isActive;

    @NotBlank(message = "Gender parameter is mandatory for the request.")
    private Gender gender;

    @NotBlank(message = "Segment parameter is mandatory for the request.")
    private Segment segment;

    @NotBlank(message = "First name parameter is mandatory for the request.")
    private Long designation;

    private List<Long> projects;

    private List<Long> skills;

    @NotBlank(message = "First name parameter is mandatory for the request.")
    private Long location;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public Long getDesignation() {
        return designation;
    }

    public void setDesignation(Long designation) {
        this.designation = designation;
    }

    public List<Long> getProjects() {
        return projects;
    }

    public void setProjects(List<Long> projects) {
        this.projects = projects;
    }

    public List<Long> getSkills() {
        return skills;
    }

    public void setSkills(List<Long> skills) {
        this.skills = skills;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }
}

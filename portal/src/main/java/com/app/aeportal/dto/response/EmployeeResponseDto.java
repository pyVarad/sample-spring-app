package com.app.aeportal.dto.response;

import com.app.aeportal.constant.DomainConstant.*;

import java.util.Collection;


public class EmployeeResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String employeeId;
    private String email;
    private boolean isActive;
    private Gender gender;
    private Segment segment;
    private DesignationResponseDto designation;
    private Collection<ProjectsResponseDto> projects;
    private Collection<SkillsResponseDto> skills;
    private LocationResponseDto location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public DesignationResponseDto getDesignation() {
        return designation;
    }

    public void setDesignation(DesignationResponseDto designation) {
        this.designation = designation;
    }

    public Collection<ProjectsResponseDto> getProjects() {
        return projects;
    }

    public void setProjects(Collection<ProjectsResponseDto> projects) {
        this.projects = projects;
    }

    public Collection<SkillsResponseDto> getSkills() {
        return skills;
    }

    public void setSkills(Collection<SkillsResponseDto> skills) {
        this.skills = skills;
    }

    public LocationResponseDto getLocation() {
        return location;
    }

    public void setLocation(LocationResponseDto location) {
        this.location = location;
    }
}

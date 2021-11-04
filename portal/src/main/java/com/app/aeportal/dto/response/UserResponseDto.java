package com.app.aeportal.dto.response;

import java.util.Collection;

public class UserResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Collection<RoleResponseDto> roles;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<RoleResponseDto> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleResponseDto> roles) {
        this.roles = roles;
    }
}

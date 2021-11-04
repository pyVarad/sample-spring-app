package com.app.aeportal.dto.request;

import com.app.aeportal.domain.Users;

import javax.validation.constraints.NotBlank;
import java.util.Collection;

public class UserRequestDto {

    @NotBlank(message = "The firstName parameter is mandatory for the request")
    private String firstName;

    @NotBlank(message = "The lastName parameter is mandatory for the request")
    private String lastName;

    @NotBlank(message = "The username parameter is mandatory for the request")
    private String username;

    @NotBlank(message = "The password parameter is mandatory for the request")
    private String password;

    private Collection<Long> roles;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Long> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Long> roles) {
        this.roles = roles;
    }

    public Users toUsers() {
        return new Users(
                firstName,
                lastName,
                username,
                password
        );
    }
}

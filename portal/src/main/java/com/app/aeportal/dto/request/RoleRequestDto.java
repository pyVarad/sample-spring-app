package com.app.aeportal.dto.request;

import com.app.aeportal.domain.Roles;

import javax.validation.constraints.NotBlank;

public class RoleRequestDto {

    @NotBlank(message = "The field roleName is mandatory parameter for the request.")
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Roles toRole() {
        return new Roles(
                roleName
        );
    }
}

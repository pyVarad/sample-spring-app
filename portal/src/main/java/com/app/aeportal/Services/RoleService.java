package com.app.aeportal.Services;

import com.app.aeportal.dto.request.RoleRequestDto;
import com.app.aeportal.dto.response.RoleResponseDto;

public interface RoleService {

    RoleResponseDto[] getAllRoles();
    RoleResponseDto getRoleById(Long id);
    RoleResponseDto addNewRole(RoleRequestDto request);
    RoleResponseDto updatedRole(Long id, RoleRequestDto request);
    void deleteRole(Long id);
}

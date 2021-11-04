package com.app.aeportal.Services.impl;

import com.app.aeportal.Services.RoleService;
import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.Roles;
import com.app.aeportal.dto.request.RoleRequestDto;
import com.app.aeportal.dto.response.RoleResponseDto;
import com.app.aeportal.repository.RolesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {

    private final RolesRepository rolesRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public RoleServiceImpl(
            RolesRepository rolesRepository,
            ObjectMapper objectMapper
    ) {
        this.rolesRepository = rolesRepository;
        this.objectMapper = objectMapper;
    }

    private Roles getRoleForGivenId(Long id) {
        Optional<Roles> roles = this.rolesRepository.findById(id);
        return roles.orElseThrow(() -> {
            throw new NoSuchElementFoundException("Requested designation does not exist.");
        });
    }

    @Override
    public RoleResponseDto[] getAllRoles() {
        return this.objectMapper.convertValue(this.rolesRepository.findAll(), RoleResponseDto[].class);
    }

    @Override
    public RoleResponseDto getRoleById(Long id) {
        return this.objectMapper.convertValue(this.getRoleForGivenId(id), RoleResponseDto.class);
    }

    @Override
    public RoleResponseDto addNewRole(RoleRequestDto request) {
        Roles roles = request.toRole();
        return this.objectMapper.convertValue(this.rolesRepository.save(roles), RoleResponseDto.class);
    }

    @Override
    public RoleResponseDto updatedRole(Long id, RoleRequestDto request) {
        Roles role = this.getRoleForGivenId(id);
        Roles updatedRole = request.toRole();
        updatedRole.setId(role.getId());
        return this.objectMapper.convertValue(this.rolesRepository.save(updatedRole), RoleResponseDto.class);
    }

    @Override
    public void deleteRole(Long id) {
        Roles role = this.getRoleForGivenId(id);
        this.rolesRepository.delete(role);
    }
}

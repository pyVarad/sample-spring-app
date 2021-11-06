package com.app.aeportal.services.impl;

import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.Roles;
import com.app.aeportal.dto.request.RoleRequestDto;
import com.app.aeportal.dto.response.RoleResponseDto;
import com.app.aeportal.repository.RolesRepository;
import com.app.aeportal.services.RoleService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {

    private final RolesRepository rolesRepository;

    @Autowired
    public RoleServiceImpl(
            RolesRepository rolesRepository
    ) {
        this.rolesRepository = rolesRepository;
    }

    private Roles getRoleForGivenId(Long id) {
        Optional<Roles> roles = this.rolesRepository.findById(id);
        return roles.orElseThrow(() -> {
            throw new NoSuchElementFoundException("Requested designation does not exist.");
        });
    }

    @Override
    public RoleResponseDto[] getAllRoles() {
        return new ModelMapper().map(this.rolesRepository.findAll(), RoleResponseDto[].class);
    }

    @Override
    public RoleResponseDto getRoleById(Long id) {
        return new ModelMapper().map(this.getRoleForGivenId(id), RoleResponseDto.class);
    }

    @Override
    public RoleResponseDto addNewRole(RoleRequestDto request) {
        Roles roles = request.toRole();
        return new ModelMapper().map(this.rolesRepository.save(roles), RoleResponseDto.class);
    }

    @Override
    public RoleResponseDto updatedRole(Long id, RoleRequestDto request) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        Roles role = this.getRoleForGivenId(id);
        modelMapper.map(request.toRole(), role);
        return new ModelMapper().map(this.rolesRepository.save(role), RoleResponseDto.class);
    }

    @Override
    public void deleteRole(Long id) {
        Roles role = this.getRoleForGivenId(id);
        this.rolesRepository.delete(role);
    }
}

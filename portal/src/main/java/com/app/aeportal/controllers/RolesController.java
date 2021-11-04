package com.app.aeportal.controllers;


import com.app.aeportal.Services.RoleService;
import com.app.aeportal.dto.request.RoleRequestDto;
import com.app.aeportal.dto.response.RoleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/roles")
public class RolesController {

    private final RoleService roleService;

    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<RoleResponseDto[]> getAllRoles() {
        return ResponseEntity.ok().body(this.roleService.getAllRoles());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.roleService.getRoleById(id));
    }

    @PostMapping
    public ResponseEntity<RoleResponseDto> addNewRole(@RequestBody @Valid RoleRequestDto request) {
        return new ResponseEntity<>(this.roleService.addNewRole(request), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RoleResponseDto> updateRole(
            @PathVariable("id") Long id,
            @RequestBody RoleRequestDto request
    ) {
        return ResponseEntity.ok().body(this.roleService.updatedRole(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RoleResponseDto> deleteRole(@PathVariable("id") Long id) {
        this.roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}

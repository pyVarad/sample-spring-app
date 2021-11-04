package com.app.aeportal.controllers;


import com.app.aeportal.Services.EmployeeService;
import com.app.aeportal.dto.request.EmployeeRequestDto;
import com.app.aeportal.dto.response.EmployeeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(
            EmployeeService employeeService
    ) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<EmployeeResponseDto[]> getAllEmployees() {
        return ResponseEntity.ok().body(this.employeeService.getAllEmployees());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.employeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> addNewEmployee(@Valid @RequestBody EmployeeRequestDto request) {
        return new ResponseEntity<>(this.employeeService.addNewEmployee(request), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployeeInfo(
            @PathVariable("id") Long id,
            @RequestBody EmployeeRequestDto request
    ) {
        return ResponseEntity.ok().body(this.employeeService.updateEmployeeInfo(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EmployeeResponseDto> deleteEmployee(@PathVariable("id") Long id) {
        this.employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}

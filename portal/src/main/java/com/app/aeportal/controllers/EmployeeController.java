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
@RequestMapping("/employees")
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
        return new ResponseEntity<>(this.employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(this.employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> addNewEmployee(@Valid @RequestBody EmployeeRequestDto request) {
        return new ResponseEntity<>(this.employeeService.addNewEmployee(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployeeInfo(
            @PathVariable Long id,
            @RequestBody EmployeeRequestDto request
    ) {
        return new ResponseEntity<>(this.employeeService.updateEmployeeInfo(id, request), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EmployeeResponseDto> deleteEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(this.employeeService.deleteEmployee(id), HttpStatus.NO_CONTENT);
    }
}

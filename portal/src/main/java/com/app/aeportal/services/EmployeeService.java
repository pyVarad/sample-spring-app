package com.app.aeportal.services;

import com.app.aeportal.dto.request.EmployeeRequestDto;
import com.app.aeportal.dto.response.EmployeeResponseDto;

public interface EmployeeService {

    EmployeeResponseDto[] getAllEmployees();
    EmployeeResponseDto addNewEmployee(EmployeeRequestDto request);
    EmployeeResponseDto updateEmployeeInfo(Long id, EmployeeRequestDto request);
    void deleteEmployee(Long id);
    EmployeeResponseDto getEmployeeById(Long id);
}

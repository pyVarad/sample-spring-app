package com.app.aeportal.Services.impl;

import com.app.aeportal.Services.EmployeeService;
import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.*;
import com.app.aeportal.dto.request.EmployeeRequestDto;
import com.app.aeportal.dto.response.EmployeeResponseDto;
import com.app.aeportal.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    private final DesignationRepository designationRepository;

    @Autowired
    private final LocationRepository locationRepository;

    @Autowired
    private final SkillsRepository skillsRepository;

    @Autowired
    private final ProjectsRepository projectsRepository;

    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            ObjectMapper objectMapper,
            DesignationRepository designationRepository,
            LocationRepository locationRepository,
            SkillsRepository skillsRepository,
            ProjectsRepository projectsRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
        this.designationRepository = designationRepository;
        this.locationRepository = locationRepository;
        this.skillsRepository = skillsRepository;
        this.projectsRepository = projectsRepository;
    }

    private Employee getEmployeeForGivenId(Long id) {
        Optional<Employee> employee = this.employeeRepository.findById(id);
        return employee.orElse(null);
    }

    private Skills getSkillsForGivenId(Long id) {
        Optional<Skills> skills = this.skillsRepository.findById(id);
        return skills.orElse(null);
    }

    private Projects getProjectById(Long id) {
        Optional<Projects> response = this.projectsRepository.findById(id);
        return response.orElse(null);
    }

    private Location getLocationForId(Long id) {
        Optional<Location> location = this.locationRepository.findById(id);
        return location.orElse(null);
    }

    private Designation getDesignationForGivenId(Long id) {
        Optional<Designation> designation = this.designationRepository.findById(id);
        return designation.orElseThrow(() -> {
            throw new NoSuchElementFoundException("Requested employee information does not exist.");
        });
    }

    private Employee createEmployeeInfo(EmployeeRequestDto request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setActive(request.isActive());
        employee.setEmployeeId(request.getEmployeeId());
        employee.setGender(request.getGender());
        employee.setSegment(request.getSegment());

        Location location = this.getLocationForId(request.getLocation());
        if(location != null) {
            employee.setLocation(location);
        }

        Designation designation = this.getDesignationForGivenId(request.getDesignation());
        if(designation != null){
            employee.setDesignation(designation);
        }

        Collection<Projects> projects = request.getProjects().stream().map(
                this::getProjectById).collect(Collectors.toCollection(ArrayList::new)
        );
        employee.setProjects(projects);

        Collection<Skills> skills = request.getSkills().stream().map(
                this::getSkillsForGivenId).collect(Collectors.toCollection(ArrayList::new)
        );
        employee.setSkills(skills);
        return employee;
    }

    @Override
    public EmployeeResponseDto[] getAllEmployees() {
        return this.objectMapper.convertValue(this.employeeRepository.findAll(), EmployeeResponseDto[].class);
    }

    @Override
    public EmployeeResponseDto addNewEmployee(EmployeeRequestDto request) {
        Employee employee = this.createEmployeeInfo(request);
        return this.objectMapper.convertValue(this.employeeRepository.save(employee), EmployeeResponseDto.class);
    }

    @Override
    public EmployeeResponseDto updateEmployeeInfo(Long id, EmployeeRequestDto request) {
        Employee employee = this.getEmployeeForGivenId(id);
        Employee updatedEmployee = this.createEmployeeInfo(request);
        updatedEmployee.setId(employee.getId());
        return this.objectMapper.convertValue(this.employeeRepository.save(employee), EmployeeResponseDto.class);
    }

    @Override
    public EmployeeResponseDto deleteEmployee(Long id) {
        Employee employee = this.getEmployeeForGivenId(id);
        this.employeeRepository.delete(employee);
        return this.objectMapper.convertValue(employee, EmployeeResponseDto.class);
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        return this.objectMapper.convertValue(this.getEmployeeForGivenId(id), EmployeeResponseDto.class);
    }
}

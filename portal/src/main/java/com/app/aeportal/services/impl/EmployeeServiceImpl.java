package com.app.aeportal.services.impl;

import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.*;
import com.app.aeportal.dto.request.EmployeeRequestDto;
import com.app.aeportal.dto.response.EmployeeResponseDto;
import com.app.aeportal.repository.*;
import com.app.aeportal.services.EmployeeService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

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
            DesignationRepository designationRepository,
            LocationRepository locationRepository,
            SkillsRepository skillsRepository,
            ProjectsRepository projectsRepository
    ) {
        this.employeeRepository = employeeRepository;
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

        Set<Projects> projects = request.getProjects().stream().map(
                this::getProjectById).collect(Collectors.toSet());
        employee.setProjects(projects);

        Set<Skills> skills = request.getSkills().stream().map(
                this::getSkillsForGivenId).collect(Collectors.toSet());
        employee.setSkills(skills);
        return employee;
    }

    @Override
    public EmployeeResponseDto[] getAllEmployees() {
        return new ModelMapper().map(this.employeeRepository.findAll(), EmployeeResponseDto[].class);
    }

    @Override
    @Transactional
    public EmployeeResponseDto addNewEmployee(EmployeeRequestDto request) {
        Employee employee = this.createEmployeeInfo(request);
        return new ModelMapper().map(this.employeeRepository.save(employee), EmployeeResponseDto.class);
    }

    @Override
    public EmployeeResponseDto updateEmployeeInfo(Long id, EmployeeRequestDto request) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        Employee employee = this.getEmployeeForGivenId(id);
        modelMapper.map(this.createEmployeeInfo(request), employee);
        return new ModelMapper().map(this.employeeRepository.save(employee), EmployeeResponseDto.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = this.getEmployeeForGivenId(id);
        this.employeeRepository.delete(employee);
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        return new ModelMapper().map(this.getEmployeeForGivenId(id), EmployeeResponseDto.class);
    }
}

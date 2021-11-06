package com.app.aeportal.controllers;


import com.app.aeportal.BaseIT;
import com.app.aeportal.constant.DomainConstant;
import com.app.aeportal.domain.*;
import com.app.aeportal.dto.response.EmployeeResponseDto;
import com.app.aeportal.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
public class TestEmployeeController extends BaseIT {

    @Autowired
    EmployeeController employeeController;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DesignationRepository designationRepository;

    @Autowired
    SkillsRepository skillsRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ProjectsRepository projectsRepository;

    @Before
    public void setUp() {
        String endDate = "2025-04-08 12:30";
        String startDate = "2021-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);

        Designation seniorManager = new Designation("Senior Manager", 22, 1);
        this.designationRepository.saveAndFlush(seniorManager);

        Skills backendJava = new Skills("Microservices", "Springboot");
        this.skillsRepository.saveAndFlush(backendJava);

        Location trivandrum = new Location("Trivandrum", "India");
        this.locationRepository.saveAndFlush(trivandrum);

        Projects nexusBanking = new Projects("Nexus for Banking",
                startDateTime, endDateTime, "1001");
        this.projectsRepository.saveAndFlush(nexusBanking);

        Employee varad = new Employee(
                "Varadarajan",
                "Govindamohan",
                "IN010M63916",
                "varad.g@test.com",
                true,
                DomainConstant.Gender.Male,
                DomainConstant.Segment.FS
        );

        varad.setDesignation(seniorManager);
        varad.setLocation(trivandrum);

        this.employeeRepository.save(varad);
    }

    @Test
    @DisplayName("Get all employees")
    public void getAllEmployees() {
        ResponseEntity<EmployeeResponseDto[]> response = this.employeeController.getAllEmployees();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @After
    public void tearDown() {
        this.employeeRepository.deleteAll();
        this.designationRepository.deleteAll();
        this.skillsRepository.deleteAll();
        this.locationRepository.deleteAll();
        this.projectsRepository.deleteAll();
    }
}

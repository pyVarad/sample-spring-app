package com.app.aeportal.controllers;


import com.app.aeportal.BaseIT;
import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.Designation;
import com.app.aeportal.dto.request.DesignationRequestDto;
import com.app.aeportal.dto.response.DesignationResponseDto;

import com.app.aeportal.repository.DesignationRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.*;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
public class TestDesignationController extends BaseIT {

    @Autowired
    DesignationController designationController;

    @Autowired
    DesignationRepository designationRepository;


    @Before
    public void setUp() {
        Designation seniorManager = new Designation("Senior Manager", 22, 1);
        this.designationRepository.save(seniorManager);

        Designation manager = new Designation("Manager", 32, 1);
        this.designationRepository.save(manager);

        Designation senior = new Designation("Senior Developer", 42, 1);
        this.designationRepository.save(senior);

        Designation staff = new Designation("Junior Developer", 52, 1);
        this.designationRepository.save(staff);
    }

    @Test
    @DisplayName("Get all designations.")
    public void getAllDesignations() {
        final ResponseEntity<DesignationResponseDto[]> response = this.designationController.getAllDesignations();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Add new designation. Check if the added designation is reflected.")
    public void addNewDesignation() {
        String registerDesignation = "test-designation";
        Integer registeredGrade = 1001;
        Integer registeredRank = 32;

        DesignationRequestDto requestDto = createDesignationRequest(registerDesignation, registeredGrade, registeredRank);
        final ResponseEntity<DesignationResponseDto> response = this.designationController.addNewDesignation(requestDto);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Long id = response.getBody().getId();

        final ResponseEntity<DesignationResponseDto> testResponse = this.designationController.getAllDesignationsById(id);
        Assertions.assertEquals(HttpStatus.OK, testResponse.getStatusCode());
        Assertions.assertNotNull(testResponse.getBody());

        String designation = testResponse.getBody().getDesignation();
        Integer grade = testResponse.getBody().getGrade();
        Integer userRank = testResponse.getBody().getUserRank();

        Assertions.assertEquals(registerDesignation, designation);
        Assertions.assertEquals(registeredGrade, grade);
        Assertions.assertEquals(registeredRank, userRank);
    }

    @Test
    @DisplayName("Update designation.")
    public void updateExistingDesignation() {

        Map<Long, DesignationResponseDto> lookup = this.designationLookup();
        List<Long> lookupKeys = new ArrayList<>(lookup.keySet());
        Long id = lookupKeys.get(0);
        DesignationResponseDto referenceObject = lookup.get(id);

        DesignationRequestDto updatedRequestDto = new DesignationRequestDto();
        updatedRequestDto.setUserRank(105);

        final ResponseEntity<DesignationResponseDto> updatedResponseDto =
                this.designationController.updateDesignation(id, updatedRequestDto);

        Assertions.assertEquals(HttpStatus.OK, updatedResponseDto.getStatusCode());
        Assertions.assertNotNull(updatedResponseDto.getBody());

        Map<Long, DesignationResponseDto> updatedLookup = this.designationLookup();
        DesignationResponseDto updatedReferenceObject = updatedLookup.get(id);
        Assertions.assertNotEquals(referenceObject.getUserRank(), updatedReferenceObject.getUserRank());
    }

    @Test
    @DisplayName("Delete designation.")
    public void deleteDesignation() {
        String registerDesignation = "test-designation-delete";
        Integer registeredGrade = 1003;
        Integer registeredRank = 34;

        DesignationRequestDto requestDto = createDesignationRequest(registerDesignation, registeredGrade, registeredRank);
        final ResponseEntity<DesignationResponseDto> response = this.designationController.addNewDesignation(requestDto);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Long id = response.getBody().getId();

        final ResponseEntity<DesignationResponseDto> testResponse = this.designationController.getAllDesignationsById(id);
        Assertions.assertEquals(HttpStatus.OK, testResponse.getStatusCode());
        Assertions.assertNotNull(testResponse.getBody());

        String designation = testResponse.getBody().getDesignation();
        Integer grade = testResponse.getBody().getGrade();
        Integer userRank = testResponse.getBody().getUserRank();

        Assertions.assertEquals(registerDesignation, designation);
        Assertions.assertEquals(registeredGrade, grade);
        Assertions.assertEquals(registeredRank, userRank);

        final ResponseEntity<DesignationResponseDto> testDeleteResponse = this.designationController.deleteDesignation(id);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, testDeleteResponse.getStatusCode());

        Collection<DesignationResponseDto> countAfterDeletion = Arrays.stream(
                Objects.requireNonNull(this.designationController.getAllDesignations().getBody())).filter(rec -> Objects.equals(rec.getId(), id))
                .collect(Collectors.toList());
        Assertions.assertEquals(countAfterDeletion.size(), 0);
    }

    @Test
    @DisplayName("Raise exception while deleting id which is not found.")
    public void shouldThrowException() {
        Assertions.assertThrows(
                NoSuchElementFoundException.class, () -> this.designationController.deleteDesignation(1000L)
        );
    }

    public Map<Long, DesignationResponseDto> designationLookup() {
        Map<Long, DesignationResponseDto> lookup = new HashMap<>();

        this.designationRepository.findAll().forEach(
                designation -> lookup.put(designation.getId(),
                        new ModelMapper().map(designation, DesignationResponseDto.class)
                )
        );

        return lookup;
    }

    public DesignationRequestDto createDesignationRequest(
            String designation,
            Integer grade,
            Integer userRank
    ) {
        DesignationRequestDto request = new DesignationRequestDto();
        request.setDesignation(designation);
        request.setGrade(grade);
        request.setUserRank(userRank);
        return request;
    }

    @After
    public void tearDown() {
        this.designationRepository.deleteAll();
    }

}

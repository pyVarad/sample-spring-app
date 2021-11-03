package com.app.aeportal.controllers;

import com.app.aeportal.Services.DesignationService;
import com.app.aeportal.dto.request.DesignationRequestDto;
import com.app.aeportal.dto.response.DesignationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/designation")
public class DesignationController {

    @Autowired
    private final DesignationService designationService;

    public DesignationController(
            DesignationService designationService
    ) {
        this.designationService = designationService;
    }

    @GetMapping
    public ResponseEntity<DesignationResponseDto[]> getAllDesignations() {
        return new ResponseEntity<>(this.designationService.getAllDesignation(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<DesignationResponseDto> getAllDesignationsById(@PathVariable Long id) {
        return new ResponseEntity<>(this.designationService.getDesignationById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DesignationResponseDto> addNewDesignation(@Valid @RequestBody DesignationRequestDto request) {
        return new ResponseEntity<>(this.designationService.addNewDesignation(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<DesignationResponseDto> updateDesignation(
            @PathVariable Long id, @RequestBody DesignationRequestDto request) {
        return new ResponseEntity<>(this.designationService.deleteDesignation(id), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DesignationResponseDto> deleteDesignation(@PathVariable Long id) {
        return new ResponseEntity<>(this.designationService.deleteDesignation(id), HttpStatus.NO_CONTENT);
    }
}

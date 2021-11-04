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
@RequestMapping("/api/v1/designation")
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
        return ResponseEntity.ok().body(this.designationService.getAllDesignation());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<DesignationResponseDto> getAllDesignationsById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.designationService.getDesignationById(id));
    }

    @PostMapping
    public ResponseEntity<DesignationResponseDto> addNewDesignation(@Valid @RequestBody DesignationRequestDto request) {
        return new ResponseEntity<>(this.designationService.addNewDesignation(request), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<DesignationResponseDto> updateDesignation(
            @PathVariable("id") Long id, @RequestBody DesignationRequestDto request) {
        return ResponseEntity.ok().body(this.designationService.updateDesignation(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DesignationResponseDto> deleteDesignation(@PathVariable("id") Long id) {
        this.designationService.deleteDesignation(id);
        return ResponseEntity.noContent().build();
    }
}

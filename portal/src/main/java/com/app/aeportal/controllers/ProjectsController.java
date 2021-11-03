package com.app.aeportal.controllers;

import com.app.aeportal.Services.ProjectsService;
import com.app.aeportal.dto.request.ProjectsRequestDto;
import com.app.aeportal.dto.response.ProjectsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private final ProjectsService projectsService;


    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @GetMapping
    public ResponseEntity<ProjectsResponseDto[]> getAllProjects() {
        return new ResponseEntity<>(this.projectsService.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectsResponseDto> getProjectsById(@PathVariable Long id) {
        return new ResponseEntity<>(this.projectsService.getProjectsById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectsResponseDto> addProjects(@Valid @RequestBody ProjectsRequestDto request) {
        return new ResponseEntity<>(this.projectsService.addNewProject(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProjectsResponseDto> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectsRequestDto request) {
        return new ResponseEntity<>(this.projectsService.updateProject(id, request), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProjectsResponseDto> deleteProject(@PathVariable Long id) {
        return new ResponseEntity<>(this.projectsService.deleteProject(id), HttpStatus.NO_CONTENT);
    }
}

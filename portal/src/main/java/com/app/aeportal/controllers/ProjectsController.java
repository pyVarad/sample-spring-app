package com.app.aeportal.controllers;

import com.app.aeportal.services.ProjectsService;
import com.app.aeportal.dto.request.ProjectsRequestDto;
import com.app.aeportal.dto.response.ProjectsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectsController {

    @Autowired
    private final ProjectsService projectsService;


    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @GetMapping
    public ResponseEntity<ProjectsResponseDto[]> getAllProjects() {
        return ResponseEntity.ok().body(this.projectsService.getAllProjects());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ProjectsResponseDto> getProjectsById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.projectsService.getProjectsById(id));
    }

    @PostMapping
    public ResponseEntity<ProjectsResponseDto> addProjects(@Valid @RequestBody ProjectsRequestDto request) {
        return new ResponseEntity<>(this.projectsService.addNewProject(request), HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ProjectsResponseDto> updateProject(
            @PathVariable("id") Long id,
            @Valid @RequestBody ProjectsRequestDto request) {
        return ResponseEntity.ok().body(this.projectsService.updateProject(id, request));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<ProjectsResponseDto> deleteProject(@PathVariable("id") Long id) {
        this.projectsService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}

package com.app.aeportal.Services.impl;

import com.app.aeportal.Services.ProjectsService;
import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.Projects;
import com.app.aeportal.dto.request.ProjectsRequestDto;
import com.app.aeportal.dto.response.ProjectsResponseDto;
import com.app.aeportal.repository.ProjectsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProjectsServiceImpl implements ProjectsService {

    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    private final ProjectsRepository projectsRepository;

    public ProjectsServiceImpl(
            ObjectMapper objectMapper,
            ProjectsRepository projectsRepository
    ) {
        this.objectMapper = objectMapper;
        this.projectsRepository = projectsRepository;
    }

    private Projects getProjectById(Long id) {
        Optional<Projects> response = this.projectsRepository.findById(id);
        return response.orElseThrow(() -> {
            throw new NoSuchElementFoundException("Requested designation does not exist.");
        });
    }

    @Override
    public ProjectsResponseDto getProjectsById(Long id) {
        return this.objectMapper.convertValue(this.getProjectById(id), ProjectsResponseDto.class);
    }

    @Override
    public ProjectsResponseDto[] getAllProjects() {
        return this.objectMapper.convertValue(this.projectsRepository.findAll(), ProjectsResponseDto[].class);
    }

    @Override
    public ProjectsResponseDto addNewProject(ProjectsRequestDto request) {
        Projects projects = request.toProjects();
        return this.objectMapper.convertValue(this.projectsRepository.save(projects), ProjectsResponseDto.class);
    }

    @Override
    @Transactional
    public ProjectsResponseDto updateProject(Long projectId, ProjectsRequestDto request) {
        Projects projects = getProjectById(projectId);
        Projects updatedProject = request.toProjects();
        updatedProject.setId(projects.getId());
        return this.objectMapper.convertValue(this.projectsRepository.save(updatedProject), ProjectsResponseDto.class);
    }

    @Override
    @Transactional
    public ProjectsResponseDto deleteProject(Long projectId) {
        Projects projects = getProjectById(projectId);
        this.projectsRepository.delete(projects);
        return this.objectMapper.convertValue(projects, ProjectsResponseDto.class);
    }
}

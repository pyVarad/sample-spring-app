package com.app.aeportal.services.impl;

import com.app.aeportal.aop.NoSuchElementFoundException;
import com.app.aeportal.domain.Projects;
import com.app.aeportal.dto.request.ProjectsRequestDto;
import com.app.aeportal.dto.response.ProjectsResponseDto;
import com.app.aeportal.repository.ProjectsRepository;
import com.app.aeportal.services.ProjectsService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProjectsServiceImpl implements ProjectsService {

    private final ProjectsRepository projectsRepository;

    @Autowired
    public ProjectsServiceImpl(
            ProjectsRepository projectsRepository
    ) {
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
        return new ModelMapper().map(this.getProjectById(id), ProjectsResponseDto.class);
    }

    @Override
    public ProjectsResponseDto[] getAllProjects() {
        return new ModelMapper().map(this.projectsRepository.findAll(), ProjectsResponseDto[].class);
    }

    @Override
    public ProjectsResponseDto addNewProject(ProjectsRequestDto request) {
        Projects projects = request.toProjects();
        return new ModelMapper().map(this.projectsRepository.save(projects), ProjectsResponseDto.class);
    }

    @Override
    @Transactional
    public ProjectsResponseDto updateProject(Long projectId, ProjectsRequestDto request) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        Projects projects = getProjectById(projectId);
        modelMapper.map(request.toProjects(), projects);
        return new ModelMapper().map(this.projectsRepository.save(projects), ProjectsResponseDto.class);
    }

    @Override
    @Transactional
    public void deleteProject(Long projectId) {
        Projects projects = getProjectById(projectId);
        this.projectsRepository.delete(projects);
    }
}

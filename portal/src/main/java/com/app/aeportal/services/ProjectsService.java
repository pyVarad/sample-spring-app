package com.app.aeportal.services;


import com.app.aeportal.dto.request.ProjectsRequestDto;
import com.app.aeportal.dto.response.ProjectsResponseDto;

public interface ProjectsService {

    ProjectsResponseDto[] getAllProjects();
    ProjectsResponseDto addNewProject(ProjectsRequestDto request);
    ProjectsResponseDto updateProject(Long projectId, ProjectsRequestDto request);
    void deleteProject(Long projectId);
    ProjectsResponseDto getProjectsById(Long id);
}

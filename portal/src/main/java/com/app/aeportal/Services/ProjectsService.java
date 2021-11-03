package com.app.aeportal.Services;


import com.app.aeportal.dto.request.ProjectsRequestDto;
import com.app.aeportal.dto.response.ProjectsResponseDto;

public interface ProjectsService {

    ProjectsResponseDto[] getAllProjects();
    ProjectsResponseDto addNewProject(ProjectsRequestDto request);
    ProjectsResponseDto updateProject(Long projectId, ProjectsRequestDto request);
    ProjectsResponseDto deleteProject(Long projectId);
    ProjectsResponseDto getProjectsById(Long id);
}

package com.app.aeportal.dto.request;

import com.app.aeportal.domain.Projects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ProjectsRequestDto {

    @NotBlank(message = "Engagement id is mandatory parameter for the request.")
    private String engagementId;

    @NotBlank(message = "Project name is mandatory parameter for the request.")
    private String projectName;

    @NotNull(message = "Start date is mandatory parameter for the request.")
    private LocalDateTime startDate;

    @NotNull(message = "End date is mandatory parameter for the request.")
    private LocalDateTime endDate;

    public ProjectsRequestDto(
            String engagementId,
            String projectName,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {
        this.engagementId = engagementId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getEngagementId() {
        return engagementId;
    }

    public void setEngagementId(String engagementId) {
        this.engagementId = engagementId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Projects toProjects() {
        return new Projects(
                projectName,
                startDate,
                endDate,
                engagementId
        );
    }
}

package com.app.aeportal.dto.response;

import java.time.LocalDateTime;

public class ProjectsResponseDto {

    private Long id;
    private String engagementId;
    private String projectName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ProjectsResponseDto(
            Long id,
            String engagementId,
            String projectName,
            LocalDateTime startDate,
            LocalDateTime endDate
    ) {
        this.id = id;
        this.engagementId = engagementId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

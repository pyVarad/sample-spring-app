package com.app.aeportal.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "projects")
public class Projects {

    @Id
    @SequenceGenerator(
            name = "projects_sequence",
            sequenceName = "project_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "project_sequence"
    )
    @Column(name = "project_id")
    private Long id;

    @Column(name = "engagement_id", nullable = false)
    private String engagementId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @ManyToMany(mappedBy = "projects")
    private Collection<Employee> employees;

    public Projects() { }

    public Projects(
            String projectName,
            LocalDateTime startDate,
            LocalDateTime endDate,
            String engagementId
    ) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.engagementId = engagementId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

    public String getEngagementId() { return engagementId; }

    public void setEngagementId(String engagementId) { this.engagementId = engagementId; }

    @Override
    public String toString() {
        return "Projects{" +
                "id=" + id +
                ", engagementId='" + engagementId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", employees=" + employees +
                '}';
    }
}

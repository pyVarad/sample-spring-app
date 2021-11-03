package com.app.aeportal.domain;

import javax.persistence.*;

import java.util.Collection;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "designation")
public class Designation {

    @Id
    @SequenceGenerator(
            name = "designation_sequence",
            sequenceName = "designation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "designation_sequence",
            strategy = SEQUENCE
    )
    @Column(name = "designation_id")
    private Long id;

    @Column(name = "designation", nullable = false)
    private String designation;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @Column(name = "user_rank", nullable = false)
    private Integer userRank;

    public Designation() { }

    public Designation(
            String designation,
            Integer grade,
            Integer userRank
    ) {
        this.designation = designation;
        this.grade = grade;
        this.userRank = userRank;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDesignation() { return designation; }

    public void setDesignation(String designation) { this.designation = designation; }

    public Integer getGrade() { return grade; }

    public void setGrade(Integer grade) { this.grade = grade; }

    public Integer getUserRank() { return userRank; }

    public void setUserRank(Integer userRank) { this.userRank = userRank; }

    @Override
    public String toString() {
        return "Designation{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", grade=" + grade +
                ", userRank=" + userRank +
                '}';
    }
}

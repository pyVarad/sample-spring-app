package com.app.aeportal.domain;

import javax.persistence.*;

import java.util.Collection;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "skills")
public class Skills {

    @Id
    @SequenceGenerator(
            name = "skills_sequence",
            sequenceName = "skills_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "skills_sequence",
            strategy = SEQUENCE
    )
    @Column(name = "skills_id")
    private Long id;

    @Column(name = "skill_category", nullable = false)
    private String skillCategory;

    @Column(name = "skill", nullable = false)
    private String skill;

//    @ManyToMany(mappedBy = "skills")
//    private Collection<Employee> employees;

    public Skills() { }

    public Skills(
            String skillCategory,
            String skill
    ) {
        this.skillCategory = skillCategory;
        this.skill = skill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillCategory() {
        return skillCategory;
    }

    public void setSkillCategory(String skillCategory) {
        this.skillCategory = skillCategory;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

//    public Collection<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(Collection<Employee> employees) {
//        this.employees = employees;
//    }

    @Override
    public String toString() {
        return "Skills{" +
                "id=" + id +
                ", skillCategory='" + skillCategory + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}

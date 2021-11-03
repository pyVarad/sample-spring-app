package com.app.aeportal.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.*;
import java.util.Collection;
import static com.app.aeportal.constant.DomainConstant.Gender;
import static com.app.aeportal.constant.DomainConstant.Segment;

@Entity(name = "Employees")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Employee {

    @Id
    @SequenceGenerator(
            name = "employee_sequence_id",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "employee_sequence"
    )
    @Column(name = "employee_id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gpn", nullable = false)
    private String employeeId;

    @Column(name = "email_address", nullable = false)
    private String email;

    @Column(name = "status", nullable = false)
    private boolean isActive;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "segment", nullable = false)
    private Segment segment;

    @OneToOne
    @JoinColumn(
            name = "designation_id", foreignKey = @ForeignKey(name = "fk_employee_designation")
    )
    private Designation designation;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_employee_employee_project")),
            inverseJoinColumns = @JoinColumn(name = "project_id", foreignKey = @ForeignKey(name = "fk_project_employee_project"))
    )
    private Collection<Projects> projects;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "employee_skills",
            joinColumns = @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_employee_employee_skills")),
            inverseJoinColumns = @JoinColumn(name = "skills_id", foreignKey = @ForeignKey(name = "fk_skills_employee_skills"))
    )
    private Collection<Skills> skills;


    @OneToOne
    @PrimaryKeyJoinColumn
    private Location location;

    public Employee() { }

    public Employee(
            String firstName,
            String lastName,
            String employeeId,
            String email,
            Boolean isActive,
            Collection<Projects> projects,
            Gender gender,
            Segment segment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.email = email;
        this.isActive = isActive;
        this.projects = projects;
        this.gender = gender;
        this.segment = segment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Collection<Projects> projects) {
        this.projects = projects;
    }

    public boolean isActive() { return isActive; }

    public void setActive(boolean active) { isActive = active; }

    public Designation getDesignation() { return designation; }

    public void setDesignation(Designation designation) { this.designation = designation; }

    public Gender getGender() { return gender; }

    public void setGender(Gender gender) { this.gender = gender; }

    public Segment getSegment() { return segment; }

    public void setSegment(Segment segment) { this.segment = segment; }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Collection<Skills> getSkills() {
        return skills;
    }

    public void setSkills(Collection<Skills> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

package com.kwgdev.projectmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kwgdev.projectmanagement.validators.UniqueValue;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Employee {

    @Id
    // AUTO lets SQL Database handle Primary Key's,
    // IDENTITY lets Hibernate manage (good with pre filled table data)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;

    @NotBlank(message="*First name required")
    @Size(min=2, max=50)
    private String firstName;
    @NotBlank(message="*Last name required")
    @Size(min=2, max=50)
    private String lastName;
    @NotBlank(message="*Must be a valid email address")
    @Email
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank(message="*Position required")
    @Size(min=2, max=50)
    private String position;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    private Manager manager;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name="project_employee",
            joinColumns=@JoinColumn(name = "employee_id"),
            inverseJoinColumns=@JoinColumn(name="project_id"))
    @JsonIgnore
    private List<Project> projects;


    public Employee(String firstName, String lastName, String email, String position, Manager manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
        this.manager = manager;
    }

    public Employee(String firstName, String lastName, String email, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
    }

    // This empty instance is used for Thymeleaf HTML form binding
    public Employee() {
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Project> getProject() {
        return projects;
    }

    public void setProject(Project project) {
        this.projects = projects;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Project> getProjects() {
        return projects;
    }

}

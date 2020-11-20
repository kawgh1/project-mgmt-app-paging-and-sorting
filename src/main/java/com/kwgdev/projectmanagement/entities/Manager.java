package com.kwgdev.projectmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
public class Manager {

    @Id
    // AUTO lets SQL Database handle Primary Key's,
    // IDENTITY lets Hibernate manage (good with pre filled table data)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long managerId;

    @NotBlank(message="*First name required")
    @Size(min=2, max=50)
    private String firstName;
    @NotBlank(message="*Last name required")
    @Size(min=2, max=50)
    private String lastName;
    @NotBlank
    @Email(message="*Must be a valid email address")
    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "manager")
    private List<Employee> employees;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "project_manager",
            joinColumns = @JoinColumn(name = "manager_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    @JsonIgnore
    private List<Project> projects;





    public Manager(String firstName, String lastName, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Manager() {
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
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





    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        if(!this.employees.contains(employee)){
            this.employees.add(employee);
        }

    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        if (!this.projects.contains(project)){
            this.projects.add(project);
        }

    }

}

package com.kwgdev.projectmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
public class Project {
    @Id
    // AUTO lets SQL Database handle Primary Key's,
    // IDENTITY lets Hibernate manage (good with pre filled table data)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long projectId;
    @NotBlank(message="*Project name required")
    @Size(min=2, max=50)
    private String name;
    @NotBlank(message="*Project location required")
    @Size(min=2, max=50)
    private String location;

    @NotBlank(message="*Project stage required")
    private String stage; // NOTSTARTED, COMPLETED, INPROGRESS

    @NotBlank(message="*Project description required")
    @Size(min=2, max=250)
    private String description;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
    fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
    joinColumns = @JoinColumn(name = "project_id"),
    inverseJoinColumns = @JoinColumn(name = "employee_id"))
    @JsonIgnore
    private List<Employee> employees;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "project_manager",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "manager_id"))
    @JsonIgnore
    private List<Manager> managers;



    // This empty instance is used for Thymeleaf HTML form binding
    public Project() {

    }

    public Project(String name, String stage, String description) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public Project(String name, String location, String stage, String description, List<Manager> managers, List<Employee> employees) {
        this.name = name;
        this.location = location;
        this.stage = stage;
        this.description = description;
        this.managers = managers;
        this.employees = employees;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public void addManager(Manager manager) {
        if(!managers.contains(manager)) {
            managers.add(manager);
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        if(!employees.contains(employee)) {
            employees.add(employee);
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

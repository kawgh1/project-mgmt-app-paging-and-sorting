package com.kwgdev.projectmanagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwgdev.projectmanagement.dto.ChartData;
import com.kwgdev.projectmanagement.dto.EmployeeProject;
import com.kwgdev.projectmanagement.dto.ManagerProject;
import com.kwgdev.projectmanagement.entities.Project;
import com.kwgdev.projectmanagement.service.EmployeeService;
import com.kwgdev.projectmanagement.service.ManagerService;
import com.kwgdev.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    // wired in from application.properties for demo purposes
    @Value("${version}")
    private String version;

//    @Autowired
//    ProjectRepository projectRepo;
    // abstraction layer to keep Controllers separated from Database operations
    @Autowired
    private ProjectService projectService;

//    @Autowired
//    ManagerRepository managerRepo;
    @Autowired
    private ManagerService managerService;

//    @Autowired
//    EmployeeRepository employeeRepo;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

//        Map<String, Object> map = new HashMap<>();
        model.addAttribute("versionNumber", version);

        // Projects
        List<Project> projects = projectService.findAll();
        model.addAttribute("projectsList", projects);

        // Managers
//        List<Manager> managers = managerRepo.findAll();
//        model.addAttribute("managersList", managers);

        // dto chart data
        List<ChartData> projectData = projectService.getProjectStatus();
        List<ChartData> employeeData = employeeService.getEmployeeStatus();
        List<ChartData> managerData = managerService.getManagerStatus();

        // convert projectData object into JSON structure for us in javascript
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);

        ObjectMapper objectMapper1 = new ObjectMapper();
        String jsonString1 = objectMapper1.writeValueAsString(employeeData);

        ObjectMapper objectMapper2 = new ObjectMapper();
        String jsonString2 = objectMapper2.writeValueAsString(managerData);
        // JSON String will look like [["NOTSTARTED", 1, "INPROGRESS", 2, "COMPLETED", 3]]

        // sned ChartData JSON data to model to display in our HTML templates
        model.addAttribute("projectStatusCount", jsonString);
        model.addAttribute("employeeStatusCount", jsonString1);
        model.addAttribute("managerStatusCount", jsonString2);

        // Employees
//        List<Employee> employees = employeeRepo.findAll();
        // we are querying the database for employees
        // -> this shows "Project Count" in Employee table on home.html
        // nothing to do with the pie charts
        List<EmployeeProject> employeesProjectCount = employeeService.getEmployeeProjects();
        model.addAttribute("employeesListProjectCount", employeesProjectCount);

        List<ManagerProject> managersProjectCount = managerService.getManagerProjects();
        model.addAttribute("managersListProjectCount", managersProjectCount);


        return "main/home";

    }
}

package com.kwgdev.projectmanagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwgdev.projectmanagement.dto.TimeChartData;
import com.kwgdev.projectmanagement.entities.Employee;
import com.kwgdev.projectmanagement.entities.Manager;
import com.kwgdev.projectmanagement.entities.Project;
import com.kwgdev.projectmanagement.service.EmployeeService;
import com.kwgdev.projectmanagement.service.ManagerService;
import com.kwgdev.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

//    @Autowired
//    ProjectRepository projectRepo;
    // abstraction layer to keep Controllers separated from Database operations
    @Autowired
    private ProjectService projectService;

//    @Autowired
//    EmployeeRepository employeeRepo;
    @Autowired
    private EmployeeService employeeService;

//    @Autowired
//    ManagerRepository managerRepo;
    @Autowired
    private ManagerService managerService;


    @GetMapping
    public String displayProjects(Model model) {
        // original un-paginated
//        List<Project> projects = projectService.findAll();
//        model.addAttribute("projects", projects);
//        return "projects/list-projects";z

        // we set default sort to ascending by project name
        return findPaginated(1, "name", "asc", model);

    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) { // Model is used to bind the Java object (Project) to the HTML Form


        Project aProject = new Project();
        List<Employee> employees = employeeService.findAll();
        List<Manager> managers = managerService.findAll();
        // bind an empty Project object to the HTML form
        model.addAttribute("project", aProject);
        model.addAttribute("allEmployees", employees);
        model.addAttribute("allManagers", managers);

        return "projects/new-project";
    }

    @PostMapping("/save-new")
    public String createProject(@Valid Project project, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "projects/new-project";
        }
        // this will handle saving the new project to the database
        projectService.save(project);

    // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
    return "redirect:/projects";
    }

    @GetMapping("/update")
    public String displayProjectUpdateForm(@RequestParam("proId") long theProId, Model model) {

        // display project
        Project thePro = projectService.findByProjectId(theProId);
        model.addAttribute("project", thePro);

        // display all managers
        List<Manager> managers = managerService.findAll();
        model.addAttribute("allManagers", managers);

        // display all employees
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("allEmployees", employees);

        return "projects/update-project";
    }

    @PostMapping("/save-update")
    public String updateProject(@Valid Project project, @RequestParam List<Manager> projectManagers,
                                Errors errors, Model model) {


//        // display all managers
//        List<Manager> managers = managerService.findAll();
//        model.addAttribute("allManagers", managers);
//
//        // display all employees
//        List<Employee> employees = employeeService.findAll();
//        model.addAttribute("allEmployees", employees);

        if (errors.hasErrors()) {
            return "projects/update-project";
        }

//        project.setEmployees(projectEmployees);
//        project.setManagers(projectManagers);




        // this will handle saving the updated project to the database
        projectService.save(project);


//        // if project is assigned a new manager from the update page,
//        // update that manager to contain this project
        for(Manager manager : projectManagers) {
            manager.addProject(project);
            managerService.save(manager);
        }
//
//        for(Manager manager : managers) {
//            // ignore remove project if manager just received this project in update
//            if(projectManagers.contains(manager)) {
//                continue;
//            }
//            // if old manager had project, remove that project
//            if (manager.getProjects().contains(project)) {
//                manager.removeProject(project);
//            }
//            continue;
//        }





        // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
        return "redirect:/projects";
    }

    @GetMapping("/delete")
    public String deleteProject(@RequestParam("proId") long theProId) {

        Project thePro = projectService.findByProjectId(theProId);
        projectService.delete(thePro);

        return "redirect:/projects";
    }

    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model){

        int pageSize = 5;

        Page<Project> page = projectService.findPaginated(pageNumber, pageSize, sortField, sortDir);

        List<Project> projects = page.getContent();

        // paging attributes used in thymeleaf template list-employees.html
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        // sorting attributes used in thymeleaf template list-employees.html
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");


        model.addAttribute("projects", projects);

        // updated displayEmployees() method at top of page to adjust for
        // paginated views
        return "projects/list-projects";

    }




//    // Function to remove duplicates from an ArrayList
//    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
//
//        // Create a new ArrayList
//        ArrayList<T> newList = new ArrayList<T>();
//        // Traverse through the first list
//        for (T element : list) {
//            // If this element is not present in newList
//            // then add it
//            if (!newList.contains(element)) {
//                newList.add(element);
//            }
//        }
//        // return the new list
//        return newList;
//    }

}

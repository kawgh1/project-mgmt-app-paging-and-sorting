package com.kwgdev.projectmanagement.controllers;

import com.kwgdev.projectmanagement.entities.Employee;
import com.kwgdev.projectmanagement.entities.Manager;
import com.kwgdev.projectmanagement.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/managers")
public class ManagerController {

//    @Autowired
//    ManagerRepository managerRepo;
    // abstraction layer to keep Controllers separated from Database operations
    @Autowired
    private ManagerService managerService;

    @GetMapping
    public String displayManagers(Model model) {
        // original un-paginated
//        List<Manager> managers = managerService.findAll();
//        model.addAttribute("managers", managers);
//        return "managers/list-managers";

        // we set default sort to ascending by first name
        return findPaginated(1, "firstName", "asc", model);

    }

    @GetMapping("/new")
    public String displayManagerForm(Model model) { // Model is used to bind the Java object (Employee) to the HTML Form


        Manager aManager = new Manager();
        // bind an empty Project object to the HTML form
        model.addAttribute("manager", aManager);

        return "managers/new-manager";
    }

    @PostMapping("/save-new")
    public String createManager(@Valid Manager manager, Errors errors, Model model) {

        if(errors.hasErrors()) {
            return "managers/new-manager";
        }

        // this will handle saving the new employee to the database
        managerService.save(manager);

        // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
        return "redirect:/managers";
    }

    @GetMapping("/update")
    public String displayManagerUpdateForm(@RequestParam("mgrId") long theMgrId, Model model) {

        // display manager
        Manager theMgr = managerService.findManagerById(theMgrId);
        model.addAttribute("manager", theMgr);

        return "managers/update-manager";
    }

    @PostMapping("/save-update")
    public String updateManager(@Valid Manager manager, Errors errors, Model model) {

        if(errors.hasErrors()) {
            return "managers/update-manager";
        }

        // this will handle saving the new employee to the database
        managerService.save(manager);

        // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
        return "redirect:/managers";
    }

    @GetMapping("/delete")
    public String deleteManager(@RequestParam("mgrId") long theMgrId) {

        Manager theMgr = managerService.findManagerById(theMgrId);
        managerService.delete(theMgr);

        return "redirect:/managers";
    }

    // Pagination and Sorting

    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model){

        int pageSize = 5;

        Page<Manager> page = managerService.findPaginated(pageNumber, pageSize, sortField, sortDir);

        List<Manager> managers = page.getContent();

        // paging attributes used in thymeleaf template list-employees.html
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        // sorting attributes used in thymeleaf template list-employees.html
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");


        model.addAttribute("managers", managers);

        // updated displayEmployees() method at top of page to adjust for
        // paginated views
        return "managers/list-managers";

    }

}

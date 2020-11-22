package com.kwgdev.projectmanagement.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwgdev.projectmanagement.dto.TimeChartData;
import com.kwgdev.projectmanagement.entities.Project;
import com.kwgdev.projectmanagement.service.EmployeeService;
import com.kwgdev.projectmanagement.service.ManagerService;
import com.kwgdev.projectmanagement.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/timelines")
public class TimelineController {

    @Autowired
    private ProjectService projectService;

    // GOogle timelines method
    @GetMapping
    public String displayProjectTimelines(Model model) throws JsonProcessingException {

        // list projects
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects", projects);

        // list google timelines

        List<TimeChartData> timelineData = projectService.getTimeData();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonTimelineString = objectMapper.writeValueAsString(timelineData);

        System.out.println("--------- project timelines ---------");
        System.out.println(jsonTimelineString);

        model.addAttribute("projectTimeList", jsonTimelineString);

//        return findPaginated(1, "name", "asc", model);

        return "timelines/project-timelines";
    }

//    @GetMapping("/page/{pageNumber}")
//    public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber,
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
//                                Model model){
//
//        int pageSize = 5;
//
//        Page<Project> page = projectService.findPaginated(pageNumber, pageSize, sortField, sortDir);
//
//        List<Project> projects = page.getContent();
//
//        // paging attributes used in thymeleaf template list-employees.html
//        model.addAttribute("currentPage", pageNumber);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//
//        // sorting attributes used in thymeleaf template list-employees.html
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//
//
//        model.addAttribute("projects", projects);
//
//        // updated displayEmployees() method at top of page to adjust for
//        // paginated views
//        return "timelines/project-timelines";
//
//    }
}

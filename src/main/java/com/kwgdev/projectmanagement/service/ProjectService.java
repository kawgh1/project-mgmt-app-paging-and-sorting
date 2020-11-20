package com.kwgdev.projectmanagement.service;

import com.kwgdev.projectmanagement.dao.ProjectRepository;
import com.kwgdev.projectmanagement.dto.ChartData;
import com.kwgdev.projectmanagement.entities.Employee;
import com.kwgdev.projectmanagement.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

// service bean
@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepo;

    public Project save(Project project) {
        return projectRepo.save(project);
    }

    public List<Project> findAll() {
        return projectRepo.findAll();
    };

    public List<ChartData> getProjectStatus() {
        return projectRepo.getProjectStatus();
    }

    public Project findByProjectId(long theProId) { return projectRepo.findByProjectId(theProId);
    }

    public void delete(Project thePro) { projectRepo.delete(thePro); }

    // Pagination and Sorting

    // front end user will see pages 1, 2, 3
    // but JPA and Hibernate use 0 based pages, 0, 1, 2 - that's why (pageNumber -1)
    public Page<Project> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {


        // first create a sort object by name, either ascending or descending depending on click

        // ternary operation
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        // return sorting and paging object
        return projectRepo.findAll(pageable);

        // return sortable object only
//        return projectRepo.findAll(sort);
    }
}

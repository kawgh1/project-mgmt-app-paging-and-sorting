package com.kwgdev.projectmanagement.service;

import com.kwgdev.projectmanagement.dao.ManagerRepository;
import com.kwgdev.projectmanagement.dto.ChartData;
import com.kwgdev.projectmanagement.dto.ManagerProject;
import com.kwgdev.projectmanagement.entities.Employee;
import com.kwgdev.projectmanagement.entities.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

//service bean
@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepo;

    public Manager save(Manager manager) {
        return managerRepo.save(manager);
    }

    public List<Manager> findAll() {
        return managerRepo.findAll();
    }

    public List<ManagerProject> getManagerProjects() {
        return managerRepo.getManagerProjects();
    }

    public List<ChartData> getManagerStatus() {
        return managerRepo.getManagerStatus();
    }

    public Manager findManagerById(long theMgrId) { return managerRepo.findByManagerId(theMgrId); }

    public void delete(Manager theMgr) { managerRepo.delete(theMgr);
    }

    // Pagination and Sorting

    // front end user will see pages 1, 2, 3
    // but JPA and Hibernate use 0 based pages, 0, 1, 2 - that's why (pageNumber -1)
    public Page<Manager> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {

        // first create a sort object by name, either ascending or descending depending on click

        // ternary operation
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        // return sorting and paging object
        return managerRepo.findAll(pageable);

        // return sortable object only
//        return managerRepo.findAll(sort);
    }
}

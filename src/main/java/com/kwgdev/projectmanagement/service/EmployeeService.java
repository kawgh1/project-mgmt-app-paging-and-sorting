package com.kwgdev.projectmanagement.service;

import com.kwgdev.projectmanagement.dao.EmployeeRepository;
import com.kwgdev.projectmanagement.dto.ChartData;
import com.kwgdev.projectmanagement.dto.EmployeeProject;
import com.kwgdev.projectmanagement.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

// service bean
@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;

    public Employee save (Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public Employee findByEmployeeId(long theEmpId) { return employeeRepo.findByEmployeeId(theEmpId);}

    public List<EmployeeProject> getEmployeeProjects() {
        return employeeRepo.getEmployeeProjects();
    }

    public List<ChartData> getEmployeeStatus() {
        return employeeRepo.getEmployeeStatus();
    }


    public void delete(Employee theEmp) {
        employeeRepo.delete(theEmp);
    }

    // Pagination and Sorting

    // front end user will see pages 1, 2, 3
    // but JPA and Hibernate use 0 based pages, 0, 1, 2 - that's why (pageNumber -1)


    public Page<Employee> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {

        // first create a sort object by name, either ascending or descending depending on click

        // ternary operation
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        // return sorting and paging object
        return employeeRepo.findAll(pageable);

        // return sortable object only
//        return employeeRepo.findAll(sort);
    }
}

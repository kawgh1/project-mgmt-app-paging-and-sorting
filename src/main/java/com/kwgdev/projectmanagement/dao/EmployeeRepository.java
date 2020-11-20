package com.kwgdev.projectmanagement.dao;

import com.kwgdev.projectmanagement.dto.ChartData;
import com.kwgdev.projectmanagement.dto.EmployeeProject;
import com.kwgdev.projectmanagement.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

// this line creates a Spring Data REST API at host/api-employees
@RepositoryRestResource(collectionResourceRel = "api-employees", path="api-employees")
//public interface EmployeeRepository extends CrudRepository<Employee, Long> {
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Override
    public List<Employee> findAll(); // default CrudRepository returns a type iterable, but we need a list

    @Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount " +
            "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    public List<EmployeeProject> getEmployeeProjects();

    @Query(nativeQuery = true, value="SELECT e.first_name, e.last_name as label, COUNT(pe.employee_id) as value " +
            "FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    public List<ChartData> getEmployeeStatus();

    public Employee findByEmployeeId(long theEmpId);

    Employee findByEmail(String value);

    // Pagination and SOrting
//    public Page<Employee> findPaginated(int pageNumber, int pageSize);

}

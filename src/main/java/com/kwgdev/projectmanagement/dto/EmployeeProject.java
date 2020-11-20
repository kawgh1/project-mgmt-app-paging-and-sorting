package com.kwgdev.projectmanagement.dto;

public interface EmployeeProject {

    // need to have property names begin with get
    // thats how Spring Data knows this DTO(Data Transfer Object) is to be used with the data
    // from the table from the custom query is EmployeeRepository
    public String getFirstName();
    public String getLastName();
    public int getProjectCount();
}

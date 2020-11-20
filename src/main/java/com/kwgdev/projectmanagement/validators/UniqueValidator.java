package com.kwgdev.projectmanagement.validators;

import com.kwgdev.projectmanagement.dao.EmployeeRepository;
import com.kwgdev.projectmanagement.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

    @Autowired
    EmployeeRepository employeeRepo;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        System.out.println("Entered Validation method...");
        Employee emp = employeeRepo.findByEmail(value);

        if(emp != null) {
            return false;
        } else {
            return true;
        }

    }
}

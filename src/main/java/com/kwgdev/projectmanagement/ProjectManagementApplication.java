package com.kwgdev.projectmanagement;

import com.kwgdev.projectmanagement.dao.EmployeeRepository;
import com.kwgdev.projectmanagement.dao.ManagerRepository;
import com.kwgdev.projectmanagement.dao.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectManagementApplication {

    @Autowired
    ProjectRepository projectRepo;

    @Autowired
    EmployeeRepository employeeRepo;

    @Autowired
    ManagerRepository managerRepo;


    public static void main(String[] args) {
        SpringApplication.run(ProjectManagementApplication.class, args);

    }

    // Instance of CommandLine Runner
//    @Bean
//    CommandLineRunner runner() {
//
//        return args -> {
//
//            Manager mgr1 = new Manager("Mark", "Hoppus", "mark@runtime.net");
//            Manager mgr2 = new Manager("Sally", "Sanders", "sally@runtime.net");
//            Manager mgr3 = new Manager("Jonathan", "Meadows", "jonathan@runtime.net");
//            Manager mgr4 = new Manager("Clarence", "Dow", "clarence@runtime.net");
//
//            Employee emp1 = new Employee("John", "Warton", "warton@gmail.com", "Tech");
//            Employee emp2 = new Employee("Mike", "Lanister", "lanister@gmail.com", "Supervisor");
//            Employee emp3 = new Employee("Steve", "Reeves", "Reeves@gmail.com", "Tech Lead");
//
//            Employee emp4 = new Employee("Ronald", "Connor", "connor@gmail.com", "Tech");
//            Employee emp5 = new Employee("Jim", "Salvator", "Sal@gmail.com", "Associate");
//            Employee emp6 = new Employee("Peter", "Henley", "henley@gmail.com", "Junior Engineer");
//
//            Employee emp7 = new Employee("Richard", "Carson", "carson@gmail.com", "Senior Engineer");
//            Employee emp8 = new Employee("Honor", "Miles", "miles@gmail.com", "Artchitect");
//            Employee emp9 = new Employee("Tony", "Roggers", "roggers@gmail.com", "Junior Architect");
//
//
//            Project pro1 = new Project("Large Production Deploy", "NOTSTARTED", "This requires all hands on deck for"
//                    + "the final deployment of the software into production");
//            Project pro2 = new Project("New Employee Budget",  "COMPLETED", "Decide on a new employee bonus budget"
//                    + "for the year and figureout who will be promoted");
//            Project pro3 = new Project("Office Reconstruction", "INPROGRESS", "The office building in Monroe has "
//                    + "been damaged due to hurricane in the region. This needs to be reconstructed");
//            Project pro4 = new Project("Improve Intranet Security", "INPROGRESS", "With the recent data hack, the office"
//                    + "security needs to be improved and proper security team needs to be hired for "
//                    + "implementation");
//
//
//
//            // employees set managers
//            emp1.setManager(mgr1);
//            emp2.setManager(mgr1);
//
//            emp3.setManager(mgr2);
//            emp4.setManager(mgr2);
//            emp5.setManager(mgr2);
//
//            emp6.setManager(mgr3);
//            emp7.setManager(mgr3);
//            emp8.setManager(mgr3);
//
//            emp9.setManager(mgr4);
//
//            // projects add employees, set managers and locations
//
//            pro1.setEmployees(Arrays.asList(emp1, emp2));
//            pro1.setManager(mgr1);
//
//            pro2.setEmployees(Arrays.asList(emp3, emp4, emp5));
//            pro2.setManager(mgr2);
//
//            pro3.setEmployees(Arrays.asList(emp6, emp7, emp8));
//            pro3.setManager(mgr3);
//
//            pro4.setEmployees(Arrays.asList(emp9));
//            pro4.setManager(mgr4);
//
//
//
//
////             managers add projects and employees
//
//            mgr1.setEmployees(Arrays.asList(emp1, emp2));
//            mgr1.setProjects(Arrays.asList(pro1));
//
//            mgr2.setEmployees(Arrays.asList(emp3, emp4, emp5));
//            mgr2.setProjects(Arrays.asList(pro2));
//
//            mgr3.setEmployees(Arrays.asList(emp6, emp7, emp8));
//            mgr3.setProjects(Arrays.asList(pro3));
//
//            mgr4.setEmployees(Arrays.asList(emp1, emp9));
//            mgr4.setProjects(Arrays.asList(pro4));
////
//
//
//
//
//
//            // save employees in database
//
//            employeeRepo.save(emp1);
//            employeeRepo.save(emp2);
//            employeeRepo.save(emp3);
//            employeeRepo.save(emp4);
//            employeeRepo.save(emp5);
//            employeeRepo.save(emp6);
//            employeeRepo.save(emp7);
//            employeeRepo.save(emp8);
//            employeeRepo.save(emp9);
//
//            // save managers in database
//            managerRepo.save(mgr1);
//            managerRepo.save(mgr2);
//            managerRepo.save(mgr3);
//            managerRepo.save(mgr4);
//
//            // save projects in database
//
//            projectRepo.save(pro1);
//            projectRepo.save(pro2);
//            projectRepo.save(pro3);
//            projectRepo.save(pro4);
//        };
//    }
}

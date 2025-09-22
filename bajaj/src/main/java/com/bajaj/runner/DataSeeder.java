package com.bajaj.runner;

import com.bajaj.model.Department;
import com.bajaj.model.Employee;
import com.bajaj.repository.DepartmentRepository;
import com.bajaj.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DataSeeder(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create departments
        Department hr = new Department();
        hr.setDepartmentName("Human Resources");

        Department it = new Department();
        it.setDepartmentName("IT");

        Department sales = new Department();
        sales.setDepartmentName("Sales");

        departmentRepository.saveAll(Arrays.asList(hr, it, sales));

        // Create employees
        Employee emp1 = new Employee();
        emp1.setFirstName("John");
        emp1.setLastName("Doe");
        emp1.setAge(30);
        emp1.setDepartment(hr);

        Employee emp2 = new Employee();
        emp2.setFirstName("Jane");
        emp2.setLastName("Smith");
        emp2.setAge(25);
        emp2.setDepartment(hr);

        Employee emp3 = new Employee();
        emp3.setFirstName("Peter");
        emp3.setLastName("Jones");
        emp3.setAge(45);
        emp3.setDepartment(hr);

        Employee emp4 = new Employee();
        emp4.setFirstName("Alice");
        emp4.setLastName("Williams");
        emp4.setAge(28);
        emp4.setDepartment(it);

        Employee emp5 = new Employee();
        emp5.setFirstName("Bob");
        emp5.setLastName("Brown");
        emp5.setAge(35);
        emp5.setDepartment(it);

        Employee emp6 = new Employee();
        emp6.setFirstName("Charlie");
        emp6.setLastName("Davis");
        emp6.setAge(22);
        emp6.setDepartment(it);

        Employee emp7 = new Employee();
        emp7.setFirstName("Eva");
        emp7.setLastName("Green");
        emp7.setAge(31);
        emp7.setDepartment(sales);

        Employee emp8 = new Employee();
        emp8.setFirstName("Frank");
        emp8.setLastName("White");
        emp8.setAge(29);
        emp8.setDepartment(sales);

        employeeRepository.saveAll(Arrays.asList(emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8));

        System.out.println("Database seeded with sample employee and department data.");
    }
}

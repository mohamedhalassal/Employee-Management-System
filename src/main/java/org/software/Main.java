package org.software;

import org.software.database.DatabaseConnectionManager;
import org.software.employee.Contractor;
import org.software.employee.Employee;
import org.software.employee.FullTimeEmployee;
import org.software.employee.PartTimeEmployee;
import org.software.repositories.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        DatabaseConnectionManager.getInstance().connect();
        EmployeeRepository employeeRepository=new EmployeeRepository();
        List<Employee>employees=employeeRepository.getAll();
       List<Employee> filterdEmployee = employees.stream()
                .filter(employee -> employee.getDepartmentId()==3)
               .toList();
    }
}
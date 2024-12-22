package org.software.employee;

public class PartTimeEmployee extends Employee {

    public PartTimeEmployee(int id, String name, double salary, int departmentId) {
        super(id, name, salary, departmentId);
    }

    @Override
    public String getType() {
        return "parttime";
    }
}


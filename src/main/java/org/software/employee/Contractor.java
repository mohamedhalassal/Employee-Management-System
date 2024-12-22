package org.software.employee;

public class Contractor extends Employee {

    public Contractor(int id, String name, double salary, int departmentId) {
        super(id, name, salary, departmentId);
    }

    @Override
    public String getType() {
        return "contractor";
    }
}

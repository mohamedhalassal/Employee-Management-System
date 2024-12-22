package org.software.employee;

public class FullTimeEmployee extends Employee {
    public FullTimeEmployee(int id,String name, double salary,int departmentId) {
        super(id,name,salary,departmentId);
    }

    @Override
    public String getType() {
        return "fulltime";
    }
}

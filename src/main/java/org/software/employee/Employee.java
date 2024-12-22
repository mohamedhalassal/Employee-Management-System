package org.software.employee;

public abstract class Employee {
    private final int id;
    private final String name;
    private final double salary;
    private final int departmentId;


    protected Employee(int id, String name, double salary, int departmentId) {
        this.name = name;
        this.salary = salary;
        this.id = id;
        this.departmentId = departmentId;
    }


    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public abstract String getType();

    public int getDepartmentId() {
        return departmentId;
    }

}

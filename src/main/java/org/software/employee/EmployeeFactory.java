package org.software.employee;

public class EmployeeFactory {

    public static Employee createEmployee(String type, int id, String name, double salary, int departmentId) {
        return switch (type.toLowerCase()) {
            case "fulltime" -> new FullTimeEmployee(id, name, salary, departmentId);
            case "parttime" -> new PartTimeEmployee(id, name, salary, departmentId);
            case "contractor" -> new Contractor(id, name, salary, departmentId);
            default -> throw new IllegalArgumentException("Invalid employee type: " + type);
        };
    }
}

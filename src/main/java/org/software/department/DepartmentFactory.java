package org.software.department;

public class DepartmentFactory {
    public static Department createDepartment(String departmentName) {
        return switch (departmentName.toLowerCase()) {
            case "hr" -> new HRDepartment();
            case "finance" -> new FinanceDepartment();
            case "it" -> new ITDepartment();
            default -> throw new IllegalArgumentException("Invalid department name: " + departmentName);
        };
    }
}


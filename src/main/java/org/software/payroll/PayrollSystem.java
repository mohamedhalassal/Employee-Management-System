package org.software.payroll;

import org.software.database.DatabaseConnectionManager;
import org.software.employee.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PayrollSystem {

    private static PayrollSystem instance;

    // Private constructor to prevent instantiation
    private PayrollSystem() {
    }

    // Method to provide access to the single instance
    public static PayrollSystem getInstance() {
        if (instance == null) {
            instance = new PayrollSystem();
        }
        return instance;
    }

    public void processPayroll(Employee employee) {
        try {
            Connection connection = DatabaseConnectionManager.getInstance().getConnection();
            String query = "SELECT * FROM Payroll WHERE employee_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, employee.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double salary = rs.getDouble("salary");
                System.out.println("Processing payroll for: " + employee.getName() + ", Salary: $" + salary);
            } else {
                System.out.println("Payroll data not found for employee: " + employee.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

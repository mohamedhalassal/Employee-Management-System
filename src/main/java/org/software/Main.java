package org.software;

import org.software.database.DatabaseConnectionManager;
import org.software.employee.Contractor;
import org.software.employee.Employee;
import org.software.employee.FullTimeEmployee;
import org.software.employee.PartTimeEmployee;
import org.software.repositories.EmployeeRepository;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI.INSTANCE.setVisible(true);
            }
        });
    }
}
package org.software;

import org.software.employee.Employee;
import org.software.repositories.EmployeeRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI extends JFrame {

    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private EmployeeTableModel employeeTableModel;
    private JTable employeeTable;

    public static final GUI INSTANCE = new GUI();

    private GUI() {
        setTitle("Employee Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Employee> employees = employeeRepository.getAll();
        employeeTableModel = new EmployeeTableModel(employees);
        employeeTable = new JTable(employeeTableModel);

        JTextField departmentField = new JTextField();
        departmentField.setToolTipText("Enter Department ID");

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");
        JButton searchButton = new JButton("Search");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeManagementFrame().setVisible(true);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Employee employee = employeeTableModel.getEmployees().get(selectedRow);
                    employeeRepository.delete(employee);
                    employeeTableModel.setEmployees(employeeRepository.getAll());
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeManagementFrame().setVisible(true);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeeManagementFrame().setVisible(true);
            }
        });



        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(searchButton);

        panel.add(departmentField, BorderLayout.NORTH);
        panel.add(new JScrollPane(employeeTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);


        add(panel);
    }
    public void updateTable(){
        employeeTableModel.setEmployees(employeeRepository.getAll());
    }
}
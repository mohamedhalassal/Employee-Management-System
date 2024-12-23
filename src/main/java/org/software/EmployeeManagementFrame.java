package org.software;

import org.software.employee.Employee;
import org.software.employee.EmployeeFactory;
import org.software.repositories.EmployeeRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeManagementFrame extends JFrame {

    private EmployeeRepository employeeRepository = new EmployeeRepository();
    private JTextField idField, typeField, nameField, salaryField, departmentIdField;
    private JButton saveButton, searchButton, updateButton;

    public EmployeeManagementFrame() {
        setTitle("Employee Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        idField = new JTextField();
        panel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Type:"), gbc);

        gbc.gridx = 1;
        typeField = new JTextField();
        panel.add(typeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField();
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Salary:"), gbc);

        gbc.gridx = 1;
        salaryField = new JTextField();
        panel.add(salaryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Department ID:"), gbc);

        gbc.gridx = 1;
        departmentIdField = new JTextField();
        panel.add(departmentIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        saveButton = new JButton("Save");
        searchButton = new JButton("Search");
        updateButton = new JButton("Update");

        buttonPanel.add(saveButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);

        panel.add(buttonPanel, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = EmployeeFactory.createEmployee(
                        typeField.getText(),
                        1,
                        nameField.getText(),
                        Double.parseDouble(salaryField.getText()),
                        Integer.parseInt(departmentIdField.getText())
                );
                employee = employeeRepository.create(employee);
                JOptionPane.showMessageDialog(null, "Employee added successfully!");
                GUI.INSTANCE.updateTable();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = employeeRepository.getById(Integer.parseInt(idField.getText()));
                if (employee != null) {
                    typeField.setText(employee.getType());
                    nameField.setText(employee.getName());
                    salaryField.setText(String.valueOf(employee.getSalary()));
                    departmentIdField.setText(String.valueOf(employee.getDepartmentId()));
                } else {
                    JOptionPane.showMessageDialog(null, "Employee not found!");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = EmployeeFactory.createEmployee(
                        typeField.getText(),
                        Integer.parseInt(idField.getText()),
                        nameField.getText(),
                        Double.parseDouble(salaryField.getText()),
                        Integer.parseInt(departmentIdField.getText())
                );
                employee = employeeRepository.update(employee);
                JOptionPane.showMessageDialog(null, "Employee updated successfully!");
                GUI.INSTANCE.updateTable();
            }
        });

        add(panel);
    }
}
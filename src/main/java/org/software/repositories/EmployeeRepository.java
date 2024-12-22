package org.software.repositories;

import org.software.database.DatabaseConnectionManager;
import org.software.employee.Employee;
import org.software.employee.EmployeeFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeRepository {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM Employee";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Employee WHERE employee_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO Employee (employee_type, name, salary, department_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Employee SET  employee_type = ?, name = ?, salary = ?, department_id = ? WHERE employee_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Employee WHERE employee_id = ?";

    private final Connection connection;

    public EmployeeRepository() {
        this.connection = DatabaseConnectionManager.getInstance().getConnection();
    }


    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {
            while (resultSet.next()) {
                employees.add(mapResultSetToEmployee(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }


    public Employee getById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEmployee(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Employee create(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            setEmployeePreparedStatementParams(statement, employee);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return EmployeeFactory.createEmployee(employee.getType(), generatedKeys.getInt(1),  employee.getName(), employee.getSalary(), employee.getDepartmentId());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }


    public Employee update(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            setEmployeePreparedStatementParams(statement, employee);
            statement.setInt(5, employee.getId());  // employee_id for updating
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }


    public Employee delete(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    private void setEmployeePreparedStatementParams(PreparedStatement statement, Employee employee) throws SQLException {
        statement.setString(1, employee.getType());           // type
        statement.setString(2, employee.getName()); // name
        statement.setDouble(3, employee.getSalary()); // salary
        statement.setInt(4, employee.getDepartmentId());       // department_id
    }

    private Employee mapResultSetToEmployee(ResultSet resultSet) throws SQLException {
        return EmployeeFactory.createEmployee(
                resultSet.getString("employee_type"),
                resultSet.getInt("employee_id") ,
                resultSet.getString("name"),
                resultSet.getDouble("salary"),
                resultSet.getInt("department_id")
        );
    }
}


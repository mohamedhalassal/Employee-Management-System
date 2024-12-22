package org.software.payroll;

import org.software.employee.Employee;

public class PayrollAdapter {

    private final processLegacyPayroll legacyProcessor;

    public PayrollAdapter(processLegacyPayroll legacyProcessor) {
        this.legacyProcessor = legacyProcessor;
    }

    public void processPayroll(Employee employee) {
        legacyProcessor.processLegacyPayroll(employee.getName(), employee.getSalary());
    }
}

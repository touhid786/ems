package com.codingshuttle.TestingApp.services;

import com.codingshuttle.TestingApp.dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {
    // Existing methods
    EmployeeDto getEmployeeById(Long id);
    EmployeeDto createNewEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
    void deleteEmployee(Long id);

    // New method to get all employees
    List<EmployeeDto> getAllEmployees();
}

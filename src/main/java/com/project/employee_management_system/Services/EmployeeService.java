package com.project.employee_management_system.Services;

import com.project.employee_management_system.Models.Employee;
import com.project.employee_management_system.Repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo manage;
    public Employee createEmployee(Employee employee) {
        return manage.save(employee);
    }
    public List<Employee> getAllEmployees() {
        return manage.findAll();
    }
    public Employee getEmployeeById(long id) {
        return manage.findById(id).orElseThrow(() -> new RuntimeException("Employee with " + id + " not found!"));
    }
    public Employee getEmployeeByDepart(String department) {
        return manage.findByDepartment(department).orElseThrow(() -> new RuntimeException("Department not found"));
    }
    public Employee updateEmployeeById(long id, Employee emp) {
        Optional<Employee> optionalEmp = manage.findById(id);
        if (!optionalEmp.isPresent()) {
            return null;
        }
        Employee existingEmployee = optionalEmp.get();
        existingEmployee.setFirstName(emp.getFirstName());
        existingEmployee.setLastName(emp.getLastName());
        existingEmployee.setEmail(emp.getEmail());
        existingEmployee.setPhone(emp.getPhone());
        existingEmployee.setDepartment(emp.getDepartment());
        existingEmployee.setSalary(emp.getSalary());
        return manage.save(existingEmployee);
    }
    public void deleteEmployeeById(long id) {
        manage.delete(getEmployeeById(id));
    }
}
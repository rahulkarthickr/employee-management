package com.project.employee_management_system.Repositories;

import com.project.employee_management_system.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByDepartment(String department);
}

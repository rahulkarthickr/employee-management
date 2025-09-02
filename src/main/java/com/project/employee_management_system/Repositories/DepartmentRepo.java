package com.project.employee_management_system.Repositories;

import com.project.employee_management_system.Models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Department findByName(String name);
}

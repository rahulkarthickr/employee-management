package com.project.employee_management_system.Services;

import com.project.employee_management_system.Models.Department;
import com.project.employee_management_system.Repositories.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo department;
    public Department createEmployee(Department dept) {
        return department.save(dept);
    }
    public List<Department> getAllDepartment() {
        return department.findAll();
    }
    public Department getDeptById(long id) {
        return department.findById(id).orElseThrow(() -> new RuntimeException("Department with " + id + " not found!"));
    }
    public Department getDeptByName(String name) {
        return department.findByName(name);
    }
    public Department updateDeptById(long id, Department dept) {
        Optional<Department> optionalDept = department.findById(id);
        if (!optionalDept.isPresent()) {
            return null;
        }
        Department existingDept = optionalDept.get();
        existingDept.setName(dept.getName());
        existingDept.setDepartment(dept.getDepartment());
        existingDept.setLocation(dept.getLocation());
        return department.save(existingDept);
    }
    public void deleteDeptById(long id) {
        department.delete(getDeptById(id));
    }
}
package com.project.employee_management_system.Controllers;

import com.project.employee_management_system.Models.Department;
import com.project.employee_management_system.Models.Employee;
import com.project.employee_management_system.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping("/newDepartment")
    ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
        return new ResponseEntity<>(departmentService.createEmployee(department), HttpStatus.OK);
    }
    @GetMapping("/departmentList")
    ResponseEntity<List<Department>> getAllDept() {
        return new ResponseEntity<List<Department>>(departmentService.getAllDepartment(), HttpStatus.OK);
    }
    @GetMapping("/getDepartmentById/{id}")
    ResponseEntity<Department> getDeptById(@PathVariable long id) {
        return new ResponseEntity<>(departmentService.getDeptById(id), HttpStatus.OK);
    }
    @GetMapping("/getDepartmentByName/{name}")
    ResponseEntity<Department> getDeptByName(@PathVariable String name) {
        return new ResponseEntity<>(departmentService.getDeptByName(name), HttpStatus.OK);
    }
    @PutMapping("/updateDepartment/{id}")
    ResponseEntity<?> updateDeptById(@PathVariable long id, @RequestBody Department dept) {
        Department updated = departmentService.updateDeptById(id, dept);
        if(updated == null) {
            return new ResponseEntity<>("Department not found!" + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @DeleteMapping("deleteDepartment/{id}")
    void deleteEmpById(@PathVariable long id) {
        departmentService.deleteDeptById(id);
    }
}

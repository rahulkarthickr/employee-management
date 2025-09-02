package com.project.employee_management_system.Controllers;

import com.project.employee_management_system.Models.Employee;
import com.project.employee_management_system.Services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/employee")
@Slf4j // Logging
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/newEmployee")
    ResponseEntity<Employee> createEmp(@Valid @RequestBody Employee emp) {
        Employee create = employeeService.createEmployee(emp);
        return new ResponseEntity<>((create), HttpStatus.OK);
    }
    @GetMapping("/employeeList")
    ResponseEntity<List<Employee>> getAllEmp() {
        return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
    }
    @GetMapping("/getEmployeeById/{id}")
    ResponseEntity<Employee> getEmpById(@PathVariable long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }
    @GetMapping("/getEmployeeByDept/{department}")
    ResponseEntity<Employee> getEmpByDepartment(@PathVariable String department) {
        return new ResponseEntity<>(employeeService.getEmployeeByDepart(department), HttpStatus.OK);
    }
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<?> updateEmpById(@PathVariable long id, @RequestBody Employee emp) {
        Employee updated = employeeService.updateEmployeeById(id, emp);
        if (updated == null) {
            return new ResponseEntity<>("Employee not found with id: " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @DeleteMapping("/deleteEmployee/{id}")
    void deleteEmpById(@PathVariable long id) {
        employeeService.deleteEmployeeById(id);
    }
}

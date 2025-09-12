package com.project.employee_management_system.Models;

import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "FirstName should not be empty!")
    private String firstName;
    private String lastName;
    @NotBlank(message = "Email should not be empty!")
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @NotBlank(message = "Phone.No should not be empty!")
    private String phone;
    @NotBlank(message = "Department should not be empty!")
    private String department;
    @NotNull(message = "Salary should not be empty!")
    private Double salary;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
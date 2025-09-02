package com.project.employee_management_system.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Entity
@AllArgsConstructor
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue
    Long id;
    @NotBlank(message = "Name should not be empty!")
    String name;
    @NotBlank(message = "Department should not be empty!")
    String department;
    @NotBlank(message = "Location should not be empty!")
    String location;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}

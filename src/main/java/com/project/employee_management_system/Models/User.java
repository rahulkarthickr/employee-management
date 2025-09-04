package com.project.employee_management_system.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Builder
public class User {
    @Id
    @GeneratedValue
    Long id;
    @Email
    @NotBlank(message = "UserName should not be empty!")
    String userName;
    @NotBlank(message = "Password should not be empty!")
    String password;
    @NotBlank(message = "Email should not be empty!")
    String email;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

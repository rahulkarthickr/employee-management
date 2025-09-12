package com.project.employee_management_system.Services;

import com.project.employee_management_system.Models.User;
import com.project.employee_management_system.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public User createUser(User user) {
        return userRepo.save(user);
    }
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User with " + id + "not found!"));
    }
    public User getUserByName(String userName) {
        return userRepo.findByUserName(userName).orElseThrow(() -> new RuntimeException("User with " + userName + " not found!"));
    }
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User with " + email + " not found!"));
    }
}
package com.project.employee_management_system.Repositories;

import com.project.employee_management_system.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByName(String userName);
    Optional<User> findByEmail(String email);
}

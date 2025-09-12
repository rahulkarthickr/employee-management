package com.project.employee_management_system.Controllers;

import com.project.employee_management_system.Models.User;
import com.project.employee_management_system.Repositories.UserRepo;
import com.project.employee_management_system.Services.UserService;
import com.project.employee_management_system.Utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired private UserService userService;
    @Autowired private UserRepo userRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody Map<String, String> body) {
        String userName = body.get("userName");
        String password = passwordEncoder.encode(body.get("password"));
        String email = body.get("email");
        if(userRepo.findByUserName(userName).isPresent()) {
            return new ResponseEntity<>("UserName already exists!", HttpStatus.CONFLICT);
        }
        if(userRepo.findByEmail(email).isPresent()) {
            return new ResponseEntity<>("Email already exists!", HttpStatus.CONFLICT);
        }
        userService.createUser(User.builder().userName(userName).password(password).email(email).build());
        return new ResponseEntity<>("Registered Successfully!", HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody Map<String, String> body) {
        String userName = body.get("userName");
        String password = body.get("password");
        String email = body.get("email");
        var userOptional = userRepo.findByEmail(email);
        User user = userOptional.get();
        if(userOptional.isEmpty()) {
            return new ResponseEntity<>("User doesn't exists!", HttpStatus.UNAUTHORIZED);
        }
        if(!passwordEncoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>("Password doesn't match!", HttpStatus.CONFLICT);
        }
        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token:", token));
    }
}
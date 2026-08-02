package com.alumniconnect.controller;

import com.alumniconnect.dto.AlumniRegistrationRequest;
import com.alumniconnect.dto.LoginRequest;
import com.alumniconnect.dto.LoginResponse;
import com.alumniconnect.dto.StudentRegistrationRequest;
import com.alumniconnect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/student")
    public String registerStudent(@RequestBody StudentRegistrationRequest request) {
        return userService.registerStudent(request);
    }

    @PostMapping("/register/alumni")
    public String registerAlumni(@RequestBody AlumniRegistrationRequest request) {
        return userService.registerAlumni(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}
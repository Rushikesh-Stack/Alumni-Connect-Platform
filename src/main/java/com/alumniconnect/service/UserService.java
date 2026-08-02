package com.alumniconnect.service;

import com.alumniconnect.dto.AlumniRegistrationRequest;
import com.alumniconnect.dto.LoginRequest;
import com.alumniconnect.dto.LoginResponse;
import com.alumniconnect.dto.StudentRegistrationRequest;

public interface UserService {

    String registerStudent(StudentRegistrationRequest request);

    String registerAlumni(AlumniRegistrationRequest request);

    LoginResponse login(LoginRequest request);

}
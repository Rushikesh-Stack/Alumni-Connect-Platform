package com.alumniconnect.service.impl;

import com.alumniconnect.dto.AlumniRegistrationRequest;
import com.alumniconnect.dto.LoginRequest;
import com.alumniconnect.dto.LoginResponse;
import com.alumniconnect.dto.StudentRegistrationRequest;
import com.alumniconnect.service.UserService;
import org.springframework.stereotype.Service;

import com.alumniconnect.security.JwtUtil;

import com.alumniconnect.entity.*;
import com.alumniconnect.enums.Role;
import com.alumniconnect.enums.UserStatus;
import com.alumniconnect.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private AlumniProfileRepository alumniProfileRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String registerStudent(StudentRegistrationRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exists");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());

        user.setRole(Role.STUDENT);
        user.setStatus(UserStatus.PENDING);

        user = userRepository.save(user);

        StudentProfile studentProfile = new StudentProfile();

        studentProfile.setUser(user);
        studentProfile.setDepartment(department);

        studentProfile.setGraduationYear(request.getGraduationYear());
        studentProfile.setInterests(request.getInterests());
        studentProfile.setSkills(request.getSkills());
        studentProfile.setBio(request.getBio());
        studentProfile.setResumeUrl(request.getResumeUrl());
        studentProfile.setLinkedinUrl(request.getLinkedinUrl());
        studentProfile.setGithubUrl(request.getGithubUrl());
        studentProfile.setProfileImageUrl(request.getProfileImageUrl());

        studentProfileRepository.save(studentProfile);

        return "Student registered successfully";
    }

    @Override
    public String registerAlumni(AlumniRegistrationRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exists");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());

        user.setRole(Role.ALUMNI);
        user.setStatus(UserStatus.PENDING);

        user = userRepository.save(user);

        AlumniProfile alumniProfile = new AlumniProfile();

        alumniProfile.setUser(user);
        alumniProfile.setDepartment(department);

        alumniProfile.setGraduationYear(request.getGraduationYear());
        alumniProfile.setCompany(request.getCompany());
        alumniProfile.setPosition(request.getPosition());
        alumniProfile.setExperienceYears(request.getExperienceYears());
        alumniProfile.setIndustry(request.getIndustry());
        alumniProfile.setSkills(request.getSkills());
        alumniProfile.setLocation(request.getLocation());
        alumniProfile.setHigherEducation(request.getHigherEducation());
        alumniProfile.setAchievements(request.getAchievements());
        alumniProfile.setBio(request.getBio());
        alumniProfile.setCareerJourney(request.getCareerJourney());
        alumniProfile.setInternships(request.getInternships());
        alumniProfile.setCertifications(request.getCertifications());
        alumniProfile.setPlacementStrategy(request.getPlacementStrategy());
        alumniProfile.setLinkedinUrl(request.getLinkedinUrl());
        alumniProfile.setGithubUrl(request.getGithubUrl());
        alumniProfile.setProfileImageUrl(request.getProfileImageUrl());

        alumniProfileRepository.save(alumniProfile);

        return "Alumni registered successfully";

    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse(
                token,
                user.getRole().name(),
                "Login successful"
        );
    }
}
package com.alumniconnect.service.impl;

import com.alumniconnect.dto.StudentProfileResponse;
import com.alumniconnect.dto.StudentProfileUpdateRequest;
import com.alumniconnect.entity.StudentProfile;
import com.alumniconnect.entity.User;
import com.alumniconnect.enums.Role;
import com.alumniconnect.repository.StudentProfileRepository;
import com.alumniconnect.repository.UserRepository;
import com.alumniconnect.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alumniconnect.dto.StudentProfileResponse;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public StudentProfileResponse getMyProfile() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.STUDENT) {
            throw new RuntimeException("Only students can access their profile");
        }

        StudentProfile studentProfile = studentProfileRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        return convertToResponse(studentProfile);
    }

    @Override
    @Transactional
    public StudentProfileResponse getStudentProfileById(Long studentId) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User loggedInUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (loggedInUser.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only Admin can view student profiles");
        }

        StudentProfile studentProfile = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        return convertToResponse(studentProfile);
    }

    @Override
    public String updateMyProfile(StudentProfileUpdateRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.STUDENT) {
            throw new RuntimeException("Only students can update their profile");
        }

        StudentProfile studentProfile = studentProfileRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        studentProfile.setGraduationYear(request.getGraduationYear());
        studentProfile.setInterests(request.getInterests());
        studentProfile.setSkills(request.getSkills());
        studentProfile.setBio(request.getBio());
        studentProfile.setResumeUrl(request.getResumeUrl());
        studentProfile.setLinkedinUrl(request.getLinkedinUrl());
        studentProfile.setGithubUrl(request.getGithubUrl());
        studentProfile.setProfileImageUrl(request.getProfileImageUrl());

        studentProfileRepository.save(studentProfile);

        return "Profile updated successfully";
    }

    private StudentProfileResponse convertToResponse(StudentProfile studentProfile) {

        StudentProfileResponse response = new StudentProfileResponse();

        response.setUserId(studentProfile.getUser().getUserId());
        response.setFullName(studentProfile.getUser().getFullName());
        response.setEmail(studentProfile.getUser().getEmail());
        response.setPhoneNumber(studentProfile.getUser().getPhoneNumber());

        response.setDepartment(studentProfile.getDepartment().getDepartmentName());

        response.setGraduationYear(studentProfile.getGraduationYear());
        response.setInterests(studentProfile.getInterests());
        response.setSkills(studentProfile.getSkills());
        response.setBio(studentProfile.getBio());
        response.setResumeUrl(studentProfile.getResumeUrl());
        response.setLinkedinUrl(studentProfile.getLinkedinUrl());
        response.setGithubUrl(studentProfile.getGithubUrl());
        response.setProfileImageUrl(studentProfile.getProfileImageUrl());

        return response;
    }
}
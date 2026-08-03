package com.alumniconnect.controller;

import com.alumniconnect.dto.StudentProfileResponse;
import com.alumniconnect.dto.StudentProfileUpdateRequest;
import com.alumniconnect.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/profile")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping
    public StudentProfileResponse getMyProfile() {

        return studentProfileService.getMyProfile();
    }

    @PutMapping
    public String updateMyProfile(@RequestBody StudentProfileUpdateRequest request) {

        return studentProfileService.updateMyProfile(request);
    }

    @GetMapping("/{studentId}")
    public StudentProfileResponse getStudentProfileById(@PathVariable Long studentId) {

        return studentProfileService.getStudentProfileById(studentId);
    }

}
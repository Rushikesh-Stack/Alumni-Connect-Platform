package com.alumniconnect.service;

import com.alumniconnect.dto.StudentProfileResponse;
import com.alumniconnect.dto.StudentProfileUpdateRequest;

public interface StudentProfileService {

    StudentProfileResponse getMyProfile();

    StudentProfileResponse getStudentProfileById(Long studentId);

    String updateMyProfile(StudentProfileUpdateRequest request);

}
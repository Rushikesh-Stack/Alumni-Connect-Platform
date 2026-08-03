package com.alumniconnect.service;

import com.alumniconnect.dto.AlumniProfileResponse;
import com.alumniconnect.dto.AlumniProfileUpdateRequest;

import java.util.List;

public interface AlumniProfileService {

    // Profile CRUD
    AlumniProfileResponse getMyProfile();

    AlumniProfileResponse getAlumniProfileById(Long alumniId);

    String updateMyProfile(AlumniProfileUpdateRequest request);

    // Directory
    List<AlumniProfileResponse> getAllAlumni();

    // Search + Filter
    List<AlumniProfileResponse> searchAlumni(
            String name,
            String company,
            String skills,
            Long departmentId,
            Integer graduationYear,
            String location,
            Integer experienceYears
    );

}
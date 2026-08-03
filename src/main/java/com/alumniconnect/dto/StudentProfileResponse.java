package com.alumniconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProfileResponse {

    private Long userId;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String department;

    private Integer graduationYear;

    private String interests;

    private String skills;

    private String bio;

    private String resumeUrl;

    private String linkedinUrl;

    private String githubUrl;

    private String profileImageUrl;

}
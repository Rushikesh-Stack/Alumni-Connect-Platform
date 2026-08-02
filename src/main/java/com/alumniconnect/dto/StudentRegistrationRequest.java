package com.alumniconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRegistrationRequest {

    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;

    private Long departmentId;

    private Integer graduationYear;

    private String interests;

    private String bio;

    private String resumeUrl;

    private String linkedinUrl;

    private String githubUrl;

    private String profileImageUrl;

}
package com.alumniconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProfileUpdateRequest {

    private Integer graduationYear;

    private String interests;

    private String skills;

    private String bio;

    private String resumeUrl;

    private String linkedinUrl;

    private String githubUrl;

    private String profileImageUrl;

}
package com.alumniconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumniRegistrationRequest {

    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;

    private Long departmentId;

    private Integer graduationYear;

    private String company;

    private String position;

    private Integer experienceYears;

    private String industry;

    private String location;

    private String higherEducation;

    private String achievements;

    private String bio;

    private String careerJourney;

    private String internships;

    private String certifications;

    private String placementStrategy;

    private String linkedinUrl;

    private String githubUrl;

    private String profileImageUrl;

}
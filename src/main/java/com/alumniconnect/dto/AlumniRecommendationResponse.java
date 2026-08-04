package com.alumniconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumniRecommendationResponse {

    private Long userId;

    private String fullName;

    private String company;

    private String position;

    private String department;

    private Integer graduationYear;

    private Integer experienceYears;

    private String location;

    private String skills;

    private Integer matchScore;

}
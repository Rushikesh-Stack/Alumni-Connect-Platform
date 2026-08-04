package com.alumniconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MentorRecommendationResponse {

    private Long userId;

    private String fullName;

    private String company;

    private String position;

    private Integer experienceYears;

    private String skills;

    private Integer mentorScore;

}
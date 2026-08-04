package com.alumniconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRecommendationResponse {

    private Long jobId;

    private String jobTitle;

    private String company;

    private String location;

    private String experienceRequired;

    private String requiredSkills;

    private Integer matchScore;

}


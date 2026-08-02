package com.alumniconnect.dto;

import com.alumniconnect.enums.JobType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JobRequest {

    private String jobTitle;

    private String company;

    private String location;

    private JobType jobType;

    private String experienceRequired;

    private String requiredSkills;

    private String description;

    private String applicationLink;

    private LocalDate lastDate;

}
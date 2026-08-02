package com.alumniconnect.dto;

import com.alumniconnect.enums.JobStatus;
import com.alumniconnect.enums.JobType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class JobResponse {

    private Long jobId;

    private String jobTitle;

    private String company;

    private String location;

    private JobType jobType;

    private String experienceRequired;

    private String requiredSkills;

    private String description;

    private String applicationLink;

    private LocalDate lastDate;

    private JobStatus status;

    private LocalDateTime postedAt;

    private String alumniName;

}
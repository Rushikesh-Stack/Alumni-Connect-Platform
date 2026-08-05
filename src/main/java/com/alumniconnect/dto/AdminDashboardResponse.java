package com.alumniconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDashboardResponse {

    private Long totalStudents;

    private Long totalAlumni;

    private Long totalJobs;

    private Long activeJobs;

    private Long inactiveJobs;

}
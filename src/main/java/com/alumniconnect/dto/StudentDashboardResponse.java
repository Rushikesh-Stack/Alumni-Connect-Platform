package com.alumniconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDashboardResponse {

    private Integer recommendedAlumni;

    private Integer recommendedJobs;

    private Integer recommendedMentors;

    private Integer jobSkillGapCount;

}
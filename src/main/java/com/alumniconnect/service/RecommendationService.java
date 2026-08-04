package com.alumniconnect.service;

import com.alumniconnect.dto.AlumniRecommendationResponse;
import com.alumniconnect.dto.JobRecommendationResponse;
import com.alumniconnect.dto.MentorRecommendationResponse;
import com.alumniconnect.dto.JobSkillGapResponse;
import com.alumniconnect.dto.AlumniSkillGapResponse;

import java.util.List;

public interface RecommendationService {

    List<AlumniRecommendationResponse> recommendAlumni();

    List<JobRecommendationResponse> recommendJobs();

    List<MentorRecommendationResponse> recommendMentors();

    List<JobSkillGapResponse> detectJobSkillGap();

    AlumniSkillGapResponse detectAlumniSkillGap(Long alumniUserId);

}
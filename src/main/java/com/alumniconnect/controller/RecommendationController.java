package com.alumniconnect.controller;

import com.alumniconnect.dto.AlumniRecommendationResponse;
import com.alumniconnect.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.alumniconnect.dto.JobRecommendationResponse;
import com.alumniconnect.dto.MentorRecommendationResponse;
import com.alumniconnect.dto.JobSkillGapResponse;
import com.alumniconnect.dto.AlumniSkillGapResponse;

import java.util.List;

@RestController
@RequestMapping("/api/recommend")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/alumni")
    public List<AlumniRecommendationResponse> recommendAlumni() {

        return recommendationService.recommendAlumni();

    }

    @GetMapping("/jobs")
    public List<JobRecommendationResponse> recommendJobs() {

        return recommendationService.recommendJobs();

    }

    @GetMapping("/mentors")
    public List<MentorRecommendationResponse> recommendMentors() {

        return recommendationService.recommendMentors();

    }

    @GetMapping("/skill-gap/jobs")
    public List<JobSkillGapResponse> detectJobSkillGap() {

        return recommendationService.detectJobSkillGap();

    }

    @GetMapping("/skill-gap/alumni/{userId}")
    public AlumniSkillGapResponse detectAlumniSkillGap(
            @PathVariable Long userId) {

        return recommendationService.detectAlumniSkillGap(userId);

    }

}
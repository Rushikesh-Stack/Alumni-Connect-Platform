package com.alumniconnect.service.impl;

import com.alumniconnect.dto.AdminDashboardResponse;
import com.alumniconnect.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.alumniconnect.repository.UserRepository;
import com.alumniconnect.repository.JobRepository;
import com.alumniconnect.enums.JobStatus;
import com.alumniconnect.enums.Role;
import com.alumniconnect.dto.StudentDashboardResponse;
import com.alumniconnect.service.RecommendationService;
import com.alumniconnect.dto.AlumniDashboardResponse;
import com.alumniconnect.entity.User;
import com.alumniconnect.enums.RequestStatus;
import com.alumniconnect.repository.MentorshipRequestRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;

    private final JobRepository jobRepository;

    private final RecommendationService recommendationService;

    private final MentorshipRequestRepository mentorshipRequestRepository;

    @Override
    public AdminDashboardResponse getAdminDashboard() {

        AdminDashboardResponse response = new AdminDashboardResponse();

        response.setTotalStudents(
                userRepository.countByRole(Role.STUDENT)
        );

        response.setTotalAlumni(
                userRepository.countByRole(Role.ALUMNI)
        );

        response.setTotalJobs(
                jobRepository.count()
        );

        response.setActiveJobs(
                jobRepository.countByStatus(JobStatus.ACTIVE)
        );

        response.setInactiveJobs(
                jobRepository.countByStatus(JobStatus.INACTIVE)
        );

        return response;

    }

    @Override
    public StudentDashboardResponse getStudentDashboard() {
        StudentDashboardResponse response = new StudentDashboardResponse();

        response.setRecommendedAlumni(
                recommendationService.recommendAlumni().size()
        );

        response.setRecommendedJobs(
                recommendationService.recommendJobs().size()
        );

        response.setRecommendedMentors(
                recommendationService.recommendMentors().size()
        );

        response.setJobSkillGapCount(
                recommendationService.detectJobSkillGap().size()
        );

        return response;
    }

    @Override
    public AlumniDashboardResponse getAlumniDashboard() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User alumni = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AlumniDashboardResponse response = new AlumniDashboardResponse();

        response.setPostedJobs(
                jobRepository.countByAlumni(alumni)
        );

        response.setTotalRequests(
                mentorshipRequestRepository.countByAlumni(alumni)
        );

        response.setPendingRequests(
                mentorshipRequestRepository.countByAlumniAndStatus(
                        alumni,
                        RequestStatus.PENDING
                )
        );

        response.setAcceptedRequests(
                mentorshipRequestRepository.countByAlumniAndStatus(
                        alumni,
                        RequestStatus.ACCEPTED
                )
        );

        response.setRejectedRequests(
                mentorshipRequestRepository.countByAlumniAndStatus(
                        alumni,
                        RequestStatus.REJECTED
                )
        );

        return response;

    }

}
package com.alumniconnect.service.impl;

import com.alumniconnect.dto.AlumniRecommendationResponse;
import com.alumniconnect.repository.AlumniProfileRepository;
import com.alumniconnect.repository.StudentProfileRepository;
import com.alumniconnect.repository.UserRepository;
import com.alumniconnect.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.alumniconnect.entity.StudentProfile;
import com.alumniconnect.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.alumniconnect.entity.AlumniProfile;
import com.alumniconnect.dto.JobRecommendationResponse;
import com.alumniconnect.entity.Job;
import com.alumniconnect.enums.JobStatus;
import com.alumniconnect.repository.JobRepository;
import com.alumniconnect.dto.MentorRecommendationResponse;
import com.alumniconnect.dto.JobSkillGapResponse;
import com.alumniconnect.dto.AlumniSkillGapResponse;

import java.util.ArrayList;
import java.util.Comparator;


import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final StudentProfileRepository studentProfileRepository;

    private final AlumniProfileRepository alumniProfileRepository;

    private final UserRepository userRepository;

    private final JobRepository jobRepository;

    @Override
    public List<AlumniRecommendationResponse> recommendAlumni() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        StudentProfile studentProfile = studentProfileRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        List<AlumniProfile> alumniProfiles = alumniProfileRepository.findAll();

        List<AlumniRecommendationResponse> recommendations = new ArrayList<>();

        for (AlumniProfile alumni : alumniProfiles) {

            int matchScore = 0;

            if (studentProfile.getDepartment().getDepartmentId()
                    .equals(alumni.getDepartment().getDepartmentId())) {

                matchScore += 40;

            }

            if (studentProfile.getSkills() != null && alumni.getSkills() != null) {

                String[] studentSkills = studentProfile.getSkills().split(",");

                String[] alumniSkills = alumni.getSkills().split(",");

                for (String studentSkill : studentSkills) {

                    for (String alumniSkill : alumniSkills) {

                        if (studentSkill.trim().equalsIgnoreCase(alumniSkill.trim())) {

                            matchScore += 20;
                            break;

                        }

                    }

                }

            }
            recommendations.add(convertToRecommendationResponse(alumni, matchScore));
        }

        recommendations.sort(
                Comparator.comparing(AlumniRecommendationResponse::getMatchScore)
                        .reversed()
        );

        return recommendations;
    }

    @Override
    public List<JobRecommendationResponse> recommendJobs() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        StudentProfile studentProfile = studentProfileRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        List<Job> jobs = jobRepository.findByStatus(JobStatus.ACTIVE);

        List<JobRecommendationResponse> recommendations = new ArrayList<>();

        for (Job job : jobs) {

            int matchScore = 0;

            if (studentProfile.getSkills() != null &&
                    job.getRequiredSkills() != null) {

                String[] studentSkills = studentProfile.getSkills().split(",");

                String[] jobSkills = job.getRequiredSkills().split(",");

                for (String studentSkill : studentSkills) {

                    for (String jobSkill : jobSkills) {

                        if (studentSkill.trim()
                                .equalsIgnoreCase(jobSkill.trim())) {

                            matchScore += 20;
                            break;

                        }

                    }

                }

            }
            if (matchScore > 0) {
                recommendations.add(
                        convertToJobRecommendationResponse(job, matchScore)
                );
            }

        }

        recommendations.sort(
                Comparator.comparing(JobRecommendationResponse::getMatchScore)
                        .reversed()
        );

        return recommendations;

    }

    @Override
    public List<MentorRecommendationResponse> recommendMentors() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        StudentProfile studentProfile = studentProfileRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        List<AlumniProfile> alumniProfiles = alumniProfileRepository.findAll();

        List<MentorRecommendationResponse> recommendations = new ArrayList<>();

        for (AlumniProfile alumni : alumniProfiles) {

            int mentorScore = 0;

            // Department Match
            if (studentProfile.getDepartment().getDepartmentId()
                    .equals(alumni.getDepartment().getDepartmentId())) {

                mentorScore += 20;

            }

            // Skills Match
            if (studentProfile.getSkills() != null &&
                    alumni.getSkills() != null) {

                String[] studentSkills = studentProfile.getSkills().split(",");

                String[] alumniSkills = alumni.getSkills().split(",");

                for (String studentSkill : studentSkills) {

                    for (String alumniSkill : alumniSkills) {

                        if (studentSkill.trim()
                                .equalsIgnoreCase(alumniSkill.trim())) {

                            mentorScore += 20;
                            break;

                        }

                    }

                }

            }

            // Experience Score
            if (alumni.getExperienceYears() >= 5) {

                mentorScore += 40;

            } else if (alumni.getExperienceYears() >= 2) {

                mentorScore += 20;

            } else {

                mentorScore += 10;

            }

            if (mentorScore > 10) {

                recommendations.add(
                        convertToMentorRecommendationResponse(alumni, mentorScore)
                );

            }

        }

        recommendations.sort(
                Comparator.comparing(MentorRecommendationResponse::getMentorScore)
                        .reversed()
        );

        return recommendations;

    }

    @Override
    public List<JobSkillGapResponse> detectJobSkillGap() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        StudentProfile studentProfile = studentProfileRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        List<Job> jobs = jobRepository.findByStatus(JobStatus.ACTIVE);

        List<JobSkillGapResponse> responses = new ArrayList<>();

        for (Job job : jobs) {

            List<String> missingSkills = new ArrayList<>();

            if (studentProfile.getSkills() != null &&
                    job.getRequiredSkills() != null) {

                String[] studentSkills = studentProfile.getSkills().split(",");

                String[] jobSkills = job.getRequiredSkills().split(",");

                for (String jobSkill : jobSkills) {

                    boolean skillFound = false;

                    for (String studentSkill : studentSkills) {

                        if (jobSkill.trim()
                                .equalsIgnoreCase(studentSkill.trim())) {

                            skillFound = true;
                            break;

                        }

                    }

                    if (!skillFound) {

                        missingSkills.add(jobSkill.trim());

                    }

                }

            }

            if (!missingSkills.isEmpty()) {

                responses.add(
                        convertToJobSkillGapResponse(job, missingSkills)
                );

            }

        }

        return responses;

    }

    @Override
    public AlumniSkillGapResponse detectAlumniSkillGap(Long alumniUserId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        StudentProfile studentProfile = studentProfileRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));

        AlumniProfile alumni = alumniProfileRepository.findById(alumniUserId)
                .orElseThrow(() -> new RuntimeException("Alumni not found"));

        List<String> missingSkills = new ArrayList<>();

        if (studentProfile.getSkills() != null &&
                alumni.getSkills() != null) {

            String[] studentSkills = studentProfile.getSkills().split(",");

            String[] alumniSkills = alumni.getSkills().split(",");

            for (String alumniSkill : alumniSkills) {

                boolean skillFound = false;

                for (String studentSkill : studentSkills) {

                    if (alumniSkill.trim()
                            .equalsIgnoreCase(studentSkill.trim())) {

                        skillFound = true;
                        break;

                    }

                }

                if (!skillFound) {

                    missingSkills.add(alumniSkill.trim());

                }

            }

        }

        return convertToAlumniSkillGapResponse(alumni, missingSkills);

    }


    private AlumniRecommendationResponse convertToRecommendationResponse(
            AlumniProfile alumni,
            int matchScore) {

        AlumniRecommendationResponse response = new AlumniRecommendationResponse();

        response.setUserId(alumni.getUser().getUserId());
        response.setFullName(alumni.getUser().getFullName());
        response.setCompany(alumni.getCompany());
        response.setPosition(alumni.getPosition());
        response.setDepartment(alumni.getDepartment().getDepartmentName());
        response.setGraduationYear(alumni.getGraduationYear());
        response.setExperienceYears(alumni.getExperienceYears());
        response.setLocation(alumni.getLocation());
        response.setSkills(alumni.getSkills());
        response.setMatchScore(matchScore);

        return response;

    }

    private JobRecommendationResponse convertToJobRecommendationResponse(
            Job job,
            int matchScore) {

        JobRecommendationResponse response = new JobRecommendationResponse();

        response.setJobId(job.getJobId());
        response.setJobTitle(job.getJobTitle());
        response.setCompany(job.getCompany());
        response.setLocation(job.getLocation());
        response.setExperienceRequired(job.getExperienceRequired());
        response.setRequiredSkills(job.getRequiredSkills());
        response.setMatchScore(matchScore);

        return response;

    }

    private MentorRecommendationResponse convertToMentorRecommendationResponse(
            AlumniProfile alumni,
            int mentorScore) {

        MentorRecommendationResponse response = new MentorRecommendationResponse();

        response.setUserId(alumni.getUser().getUserId());
        response.setFullName(alumni.getUser().getFullName());
        response.setCompany(alumni.getCompany());
        response.setPosition(alumni.getPosition());
        response.setExperienceYears(alumni.getExperienceYears());
        response.setSkills(alumni.getSkills());
        response.setMentorScore(mentorScore);

        return response;

    }

    private JobSkillGapResponse convertToJobSkillGapResponse(
            Job job,
            List<String> missingSkills) {

        JobSkillGapResponse response = new JobSkillGapResponse();

        response.setJobId(job.getJobId());
        response.setJobTitle(job.getJobTitle());
        response.setCompany(job.getCompany());
        response.setMissingSkills(missingSkills);

        return response;

    }

    private AlumniSkillGapResponse convertToAlumniSkillGapResponse(
            AlumniProfile alumni,
            List<String> missingSkills) {

        AlumniSkillGapResponse response = new AlumniSkillGapResponse();

        response.setUserId(alumni.getUser().getUserId());
        response.setAlumniName(alumni.getUser().getFullName());
        response.setCompany(alumni.getCompany());
        response.setPosition(alumni.getPosition());
        response.setMissingSkills(missingSkills);

        return response;

    }


}
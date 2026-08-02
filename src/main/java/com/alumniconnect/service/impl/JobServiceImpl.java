package com.alumniconnect.service.impl;

import com.alumniconnect.dto.JobRequest;
import com.alumniconnect.dto.JobResponse;
import com.alumniconnect.repository.JobRepository;
import com.alumniconnect.repository.UserRepository;
import com.alumniconnect.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alumniconnect.entity.Job;
import com.alumniconnect.entity.User;
import com.alumniconnect.enums.JobStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.alumniconnect.enums.Role;

import java.time.LocalDateTime;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createJob(JobRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User alumni = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (alumni.getRole() != Role.ALUMNI &&
                alumni.getRole() != Role.ADMIN) {

            throw new RuntimeException("Only Alumni or Admin can post jobs");
        }

        Job job = new Job();

        job.setAlumni(alumni);

        job.setJobTitle(request.getJobTitle());
        job.setCompany(request.getCompany());
        job.setLocation(request.getLocation());
        job.setJobType(request.getJobType());
        job.setExperienceRequired(request.getExperienceRequired());
        job.setRequiredSkills(request.getRequiredSkills());
        job.setDescription(request.getDescription());
        job.setApplicationLink(request.getApplicationLink());
        job.setLastDate(request.getLastDate());

        job.setStatus(JobStatus.ACTIVE);
        job.setPostedAt(LocalDateTime.now());

        jobRepository.save(job);

        return "Job posted successfully";
    }

    @Override
    public List<JobResponse> getAllJobs() {

        List<Job> jobs = jobRepository.findByStatus(JobStatus.ACTIVE);

        return jobs.stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public JobResponse getJobById(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        return convertToResponse(job);
    }

    @Override
    public String updateJob(Long jobId, JobRequest request) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User loggedInUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (loggedInUser.getRole() == Role.STUDENT) {
            throw new RuntimeException("Students cannot update jobs");
        }

        if (loggedInUser.getRole() == Role.ALUMNI &&
                !job.getAlumni().getUserId().equals(loggedInUser.getUserId())) {

            throw new RuntimeException("You can update only your own jobs");
        }

        job.setJobTitle(request.getJobTitle());
        job.setCompany(request.getCompany());
        job.setLocation(request.getLocation());
        job.setJobType(request.getJobType());
        job.setExperienceRequired(request.getExperienceRequired());
        job.setRequiredSkills(request.getRequiredSkills());
        job.setDescription(request.getDescription());
        job.setApplicationLink(request.getApplicationLink());
        job.setLastDate(request.getLastDate());

        jobRepository.save(job);

        return "Job updated successfully";
    }

    @Override
    public String deleteJob(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User loggedInUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (loggedInUser.getRole() == Role.STUDENT) {
            throw new RuntimeException("Students cannot delete jobs");
        }

        if (loggedInUser.getRole() == Role.ALUMNI &&
                !job.getAlumni().getUserId().equals(loggedInUser.getUserId())) {

            throw new RuntimeException("You can delete only your own jobs");
        }

        jobRepository.delete(job);

        return "Job deleted successfully";
    }

    private JobResponse convertToResponse(Job job) {

        JobResponse response = new JobResponse();

        response.setJobId(job.getJobId());
        response.setJobTitle(job.getJobTitle());
        response.setCompany(job.getCompany());
        response.setLocation(job.getLocation());
        response.setJobType(job.getJobType());
        response.setExperienceRequired(job.getExperienceRequired());
        response.setRequiredSkills(job.getRequiredSkills());
        response.setDescription(job.getDescription());
        response.setApplicationLink(job.getApplicationLink());
        response.setLastDate(job.getLastDate());
        response.setStatus(job.getStatus());
        response.setPostedAt(job.getPostedAt());

        response.setAlumniName(job.getAlumni().getFullName());

        return response;
    }
}
package com.alumniconnect.service;

import com.alumniconnect.dto.JobRequest;
import com.alumniconnect.dto.JobResponse;

import java.util.List;

public interface JobService {

    String createJob(JobRequest request);

    List<JobResponse> getAllJobs();

    JobResponse getJobById(Long jobId);

    String updateJob(Long jobId, JobRequest request);

    String deleteJob(Long jobId);

}


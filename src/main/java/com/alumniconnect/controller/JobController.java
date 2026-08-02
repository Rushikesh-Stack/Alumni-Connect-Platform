package com.alumniconnect.controller;

import com.alumniconnect.dto.JobRequest;
import com.alumniconnect.dto.JobResponse;
import com.alumniconnect.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public String createJob(@RequestBody JobRequest request) {

        return jobService.createJob(request);
    }

    @GetMapping
    public List<JobResponse> getAllJobs() {

        return jobService.getAllJobs();
    }

    @GetMapping("/{jobId}")
    public JobResponse getJobById(@PathVariable Long jobId) {

        return jobService.getJobById(jobId);
    }

    @PutMapping("/{jobId}")
    public String updateJob(@PathVariable Long jobId,
                            @RequestBody JobRequest request) {

        return jobService.updateJob(jobId, request);
    }

    @DeleteMapping("/{jobId}")
    public String deleteJob(@PathVariable Long jobId) {

        return jobService.deleteJob(jobId);
    }


}
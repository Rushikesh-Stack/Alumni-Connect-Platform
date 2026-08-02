package com.alumniconnect.repository;

import com.alumniconnect.entity.Job;
import com.alumniconnect.enums.JobStatus;
import com.alumniconnect.enums.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByStatus(JobStatus status);

    List<Job> findByJobType(JobType jobType);

    List<Job> findByCompanyContainingIgnoreCase(String company);

    List<Job> findByLocationContainingIgnoreCase(String location);

}
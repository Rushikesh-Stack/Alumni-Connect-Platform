package com.alumniconnect.entity;

import com.alumniconnect.enums.JobStatus;
import com.alumniconnect.enums.JobType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumni_user_id")
    private User alumni;

    @Column(name = "job_title", nullable = false, length = 150)
    private String jobTitle;

    @Column(name = "company", nullable = false, length = 100)
    private String company;

    @Column(name = "location", length = 100)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type", nullable = false)
    private JobType jobType;

    @Column(name = "experience_required", length = 50)
    private String experienceRequired;

    @Column(name = "required_skills", columnDefinition = "TEXT")
    private String requiredSkills;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "application_link", nullable = false, length = 255)
    private String applicationLink;

    @Column(name = "last_date")
    private LocalDate lastDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private JobStatus status;

    @Column(name = "posted_at")
    private LocalDateTime postedAt;

}
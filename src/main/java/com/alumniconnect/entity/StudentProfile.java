package com.alumniconnect.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student_profile")
public class StudentProfile {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "graduation_year", nullable = false)
    private Integer graduationYear;

    @Column(name = "interests", length = 255)
    private String interests;

    @Column(name = "skills", columnDefinition = "TEXT")
    private String skills;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "resume_url", length = 255)
    private String resumeUrl;

    @Column(name = "linkedin_url", length = 255)
    private String linkedinUrl;

    @Column(name = "github_url", length = 255)
    private String githubUrl;

    @Column(name = "profile_image_url", length = 255)
    private String profileImageUrl;

}
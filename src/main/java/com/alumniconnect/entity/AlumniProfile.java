package com.alumniconnect.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "alumni_profile")
public class AlumniProfile {

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

    @Column(name = "company", length = 100)
    private String company;

    @Column(name = "position", length = 100)
    private String position;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "industry", length = 100)
    private String industry;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "higher_education", length = 255)
    private String higherEducation;

    @Column(name = "achievements", columnDefinition = "TEXT")
    private String achievements;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "career_journey", columnDefinition = "TEXT")
    private String careerJourney;

    @Column(name = "internships", columnDefinition = "TEXT")
    private String internships;

    @Column(name = "certifications", columnDefinition = "TEXT")
    private String certifications;

    @Column(name = "placement_strategy", columnDefinition = "TEXT")
    private String placementStrategy;

    @Column(name = "linkedin_url", length = 255)
    private String linkedinUrl;

    @Column(name = "github_url", length = 255)
    private String githubUrl;

    @Column(name = "profile_image_url", length = 255)
    private String profileImageUrl;

}
package com.alumniconnect.entity;

import com.alumniconnect.enums.Priority;
import com.alumniconnect.enums.TargetAudience;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "announcements")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private Long announcementId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_audience", nullable = false)
    private TargetAudience targetAudience;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @Column(name = "attachment_url", length = 255)
    private String attachmentUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;
}
package com.alumniconnect.entity;

import com.alumniconnect.enums.ResourceCategory;
import com.alumniconnect.enums.ResourceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private Long resourceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumni_user_id")
    private User alumni;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type", nullable = false)
    private ResourceType resourceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private ResourceCategory category;

    @Column(name = "file_url", length = 255)
    private String fileUrl;

    @Column(name = "external_link", length = 255)
    private String externalLink;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Column(name = "download_count")
    private Integer downloadCount;
}

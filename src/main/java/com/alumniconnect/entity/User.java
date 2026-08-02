package com.alumniconnect.entity;

import com.alumniconnect.enums.Role;
import com.alumniconnect.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")

public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "phone_number", unique = true, length = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private StudentProfile studentProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AlumniProfile alumniProfile;

    @OneToMany(mappedBy = "alumni")
    private List<Job> jobs;

    @OneToMany(mappedBy = "alumni")
    private List<Resource> resources;

    @OneToMany(mappedBy = "createdBy")
    private List<Event> events;

    @OneToMany(mappedBy = "createdBy")
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "student")
    private List<MentorshipRequest> sentMentorshipRequests;

    @OneToMany(mappedBy = "alumni")
    private List<MentorshipRequest> receivedMentorshipRequests;


}

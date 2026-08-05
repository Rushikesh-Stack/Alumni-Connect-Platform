package com.alumniconnect.repository;

import com.alumniconnect.entity.MentorshipRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alumniconnect.entity.User;
import com.alumniconnect.enums.RequestStatus;

public interface MentorshipRequestRepository extends JpaRepository<MentorshipRequest, Long> {

    long countByAlumni(User alumni);

    long countByAlumniAndStatus(User alumni, RequestStatus status);

}
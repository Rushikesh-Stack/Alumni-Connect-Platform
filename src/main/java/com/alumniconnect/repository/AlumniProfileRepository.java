package com.alumniconnect.repository;

import com.alumniconnect.entity.AlumniProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumniProfileRepository extends JpaRepository<AlumniProfile, Long> {

    // Directory
    List<AlumniProfile> findAll();

    // Search
    List<AlumniProfile> findByUser_FullNameContainingIgnoreCase(String name);

    List<AlumniProfile> findByCompanyContainingIgnoreCase(String company);

    List<AlumniProfile> findBySkillsContainingIgnoreCase(String skills);

    // Filtering
    List<AlumniProfile> findByDepartment_DepartmentId(Long departmentId);

    List<AlumniProfile> findByGraduationYear(Integer graduationYear);

    List<AlumniProfile> findByLocationContainingIgnoreCase(String location);

    List<AlumniProfile> findByExperienceYears(Integer experienceYears);

}
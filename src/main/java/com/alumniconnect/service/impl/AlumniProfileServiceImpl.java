package com.alumniconnect.service.impl;

import com.alumniconnect.dto.AlumniProfileResponse;
import com.alumniconnect.dto.AlumniProfileUpdateRequest;
import com.alumniconnect.entity.AlumniProfile;
import com.alumniconnect.entity.User;
import com.alumniconnect.enums.Role;
import com.alumniconnect.repository.AlumniProfileRepository;
import com.alumniconnect.repository.UserRepository;
import com.alumniconnect.service.AlumniProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AlumniProfileServiceImpl implements AlumniProfileService {

    @Autowired
    private AlumniProfileRepository alumniProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public AlumniProfileResponse getMyProfile() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.ALUMNI) {
            throw new RuntimeException("Only alumni can access their profile");
        }

        AlumniProfile alumniProfile = alumniProfileRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Alumni profile not found"));

        return convertToResponse(alumniProfile);
    }

    @Override
    @Transactional
    public AlumniProfileResponse getAlumniProfileById(Long alumniId) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User loggedInUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (loggedInUser.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only Admin can view alumni profiles");
        }

        AlumniProfile alumniProfile = alumniProfileRepository.findById(alumniId)
                .orElseThrow(() -> new RuntimeException("Alumni profile not found"));

        return convertToResponse(alumniProfile);
    }

    @Override
    @Transactional
    public String updateMyProfile(AlumniProfileUpdateRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.ALUMNI) {
            throw new RuntimeException("Only alumni can update their profile");
        }

        AlumniProfile alumniProfile = alumniProfileRepository.findById(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Alumni profile not found"));

        alumniProfile.setGraduationYear(request.getGraduationYear());
        alumniProfile.setCompany(request.getCompany());
        alumniProfile.setPosition(request.getPosition());
        alumniProfile.setExperienceYears(request.getExperienceYears());
        alumniProfile.setIndustry(request.getIndustry());
        alumniProfile.setSkills(request.getSkills());
        alumniProfile.setLocation(request.getLocation());
        alumniProfile.setHigherEducation(request.getHigherEducation());
        alumniProfile.setAchievements(request.getAchievements());
        alumniProfile.setBio(request.getBio());
        alumniProfile.setCareerJourney(request.getCareerJourney());
        alumniProfile.setInternships(request.getInternships());
        alumniProfile.setCertifications(request.getCertifications());
        alumniProfile.setPlacementStrategy(request.getPlacementStrategy());
        alumniProfile.setLinkedinUrl(request.getLinkedinUrl());
        alumniProfile.setGithubUrl(request.getGithubUrl());
        alumniProfile.setProfileImageUrl(request.getProfileImageUrl());

        alumniProfileRepository.save(alumniProfile);

        return "Profile updated successfully";
    }

    @Override
    @Transactional
    public List<AlumniProfileResponse> getAllAlumni() {

        List<AlumniProfile> alumniProfiles = alumniProfileRepository.findAll();

        return alumniProfiles.stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<AlumniProfileResponse> searchAlumni(
            String name,
            String company,
            String skills,
            Long departmentId,
            Integer graduationYear,
            String location,
            Integer experienceYears) {

        List<AlumniProfile> alumniProfiles = alumniProfileRepository.findAll();

        return alumniProfiles.stream()

                .filter(a -> name == null || a.getUser().getFullName()
                        .toLowerCase().contains(name.toLowerCase()))

                .filter(a -> company == null || a.getCompany() != null &&
                        a.getCompany().toLowerCase().contains(company.toLowerCase()))

                .filter(a -> skills == null || a.getSkills() != null &&
                        a.getSkills().toLowerCase().contains(skills.toLowerCase()))

                .filter(a -> departmentId == null ||
                        a.getDepartment().getDepartmentId().equals(departmentId))

                .filter(a -> graduationYear == null ||
                        a.getGraduationYear().equals(graduationYear))

                .filter(a -> location == null || a.getLocation() != null &&
                        a.getLocation().toLowerCase().contains(location.toLowerCase()))

                .filter(a -> experienceYears == null ||
                        a.getExperienceYears().equals(experienceYears))

                .map(this::convertToResponse)

                .toList();
    }

    private AlumniProfileResponse convertToResponse(AlumniProfile alumniProfile) {

        AlumniProfileResponse response = new AlumniProfileResponse();

        response.setUserId(alumniProfile.getUser().getUserId());
        response.setFullName(alumniProfile.getUser().getFullName());
        response.setEmail(alumniProfile.getUser().getEmail());
        response.setPhoneNumber(alumniProfile.getUser().getPhoneNumber());

        response.setDepartment(alumniProfile.getDepartment().getDepartmentName());

        response.setGraduationYear(alumniProfile.getGraduationYear());
        response.setCompany(alumniProfile.getCompany());
        response.setPosition(alumniProfile.getPosition());
        response.setExperienceYears(alumniProfile.getExperienceYears());
        response.setIndustry(alumniProfile.getIndustry());
        response.setSkills(alumniProfile.getSkills());
        response.setLocation(alumniProfile.getLocation());
        response.setHigherEducation(alumniProfile.getHigherEducation());
        response.setAchievements(alumniProfile.getAchievements());
        response.setBio(alumniProfile.getBio());
        response.setCareerJourney(alumniProfile.getCareerJourney());
        response.setInternships(alumniProfile.getInternships());
        response.setCertifications(alumniProfile.getCertifications());
        response.setPlacementStrategy(alumniProfile.getPlacementStrategy());
        response.setLinkedinUrl(alumniProfile.getLinkedinUrl());
        response.setGithubUrl(alumniProfile.getGithubUrl());
        response.setProfileImageUrl(alumniProfile.getProfileImageUrl());

        return response;
    }

}
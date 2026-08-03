package com.alumniconnect.controller;

import com.alumniconnect.dto.AlumniProfileResponse;
import com.alumniconnect.dto.AlumniProfileUpdateRequest;
import com.alumniconnect.service.AlumniProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alumni/profile")
public class AlumniProfileController {

    @Autowired
    private AlumniProfileService alumniProfileService;

    @GetMapping
    public AlumniProfileResponse getMyProfile() {

        return alumniProfileService.getMyProfile();
    }

    @PutMapping
    public String updateMyProfile(@RequestBody AlumniProfileUpdateRequest request) {

        return alumniProfileService.updateMyProfile(request);
    }

    @GetMapping("/{alumniId}")
    public AlumniProfileResponse getAlumniProfileById(@PathVariable Long alumniId) {

        return alumniProfileService.getAlumniProfileById(alumniId);
    }

    @GetMapping("/directory")
    public List<AlumniProfileResponse> getAllAlumni() {

        return alumniProfileService.getAllAlumni();
    }

    @GetMapping("/search")
    public List<AlumniProfileResponse> searchAlumni(

            @RequestParam(required = false) String name,

            @RequestParam(required = false) String company,

            @RequestParam(required = false) String skills,

            @RequestParam(required = false) Long departmentId,

            @RequestParam(required = false) Integer graduationYear,

            @RequestParam(required = false) String location,

            @RequestParam(required = false) Integer experienceYears) {

        return alumniProfileService.searchAlumni(
                name,
                company,
                skills,
                departmentId,
                graduationYear,
                location,
                experienceYears
        );
    }
}
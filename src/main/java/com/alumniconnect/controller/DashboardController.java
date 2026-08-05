package com.alumniconnect.controller;

import com.alumniconnect.dto.AdminDashboardResponse;
import com.alumniconnect.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alumniconnect.dto.StudentDashboardResponse;
import com.alumniconnect.dto.AlumniDashboardResponse;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/admin")
    public AdminDashboardResponse getAdminDashboard() {

        return dashboardService.getAdminDashboard();

    }

    @GetMapping("/student")
    public StudentDashboardResponse getStudentDashboard() {

        return dashboardService.getStudentDashboard();

    }

    @GetMapping("/alumni")
    public AlumniDashboardResponse getAlumniDashboard() {

        return dashboardService.getAlumniDashboard();

    }

}
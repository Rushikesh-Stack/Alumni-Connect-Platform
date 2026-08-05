package com.alumniconnect.service;

import com.alumniconnect.dto.AdminDashboardResponse;
import com.alumniconnect.dto.StudentDashboardResponse;
import com.alumniconnect.dto.AlumniDashboardResponse;

public interface DashboardService {

    AdminDashboardResponse getAdminDashboard();

    StudentDashboardResponse getStudentDashboard();

    AlumniDashboardResponse getAlumniDashboard();

}
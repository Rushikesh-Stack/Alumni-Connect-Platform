package com.alumniconnect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumniDashboardResponse {

    private Long postedJobs;

    private Long totalRequests;

    private Long pendingRequests;

    private Long acceptedRequests;

    private Long rejectedRequests;

}
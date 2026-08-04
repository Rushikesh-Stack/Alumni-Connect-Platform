package com.alumniconnect.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SkillGapResponse {

    private Long jobId;

    private String jobTitle;

    private String company;

    private List<String> missingSkills;

}
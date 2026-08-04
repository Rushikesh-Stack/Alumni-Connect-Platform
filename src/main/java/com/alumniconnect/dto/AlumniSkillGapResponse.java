package com.alumniconnect.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlumniSkillGapResponse {

    private Long userId;

    private String alumniName;

    private String company;

    private String position;

    private List<String> missingSkills;

}
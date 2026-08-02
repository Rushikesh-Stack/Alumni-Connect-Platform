package com.alumniconnect.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Long skillId;

    @Column(name = "skill_name", nullable = false, unique = true, length = 100)
    private String skillName;

    @Column(name = "skill_category", length = 100)
    private String skillCategory;


}
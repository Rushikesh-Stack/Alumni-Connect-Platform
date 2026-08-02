package com.alumniconnect.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_skill")
@IdClass(UserSkillId.class)
public class UserSkill {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "skill_id")
    private Long skillId;
}
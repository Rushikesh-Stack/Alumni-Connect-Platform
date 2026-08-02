package com.alumniconnect.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserSkillId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long skillId;

}

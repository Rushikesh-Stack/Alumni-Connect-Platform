package com.alumniconnect.repository;

import com.alumniconnect.entity.UserSkill;
import com.alumniconnect.entity.UserSkillId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSkillRepository extends JpaRepository<UserSkill, UserSkillId> {

}

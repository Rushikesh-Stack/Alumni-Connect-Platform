package com.alumniconnect.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name", nullable = false, unique = true, length = 100)
    private String departmentName;

    @Column(name = "department_code", nullable = false, unique = true, length = 20)
    private String departmentCode;

    @OneToMany(mappedBy = "department")
    private List<StudentProfile> students;

    @OneToMany(mappedBy = "department")
    private List<AlumniProfile> alumni;

}
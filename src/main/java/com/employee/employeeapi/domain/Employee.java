package com.employee.employeeapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_skill",
            joinColumns = { @JoinColumn(name = "employee_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id", referencedColumnName = "id") }
    )
    private List<Skill> skill = new ArrayList<>();
}

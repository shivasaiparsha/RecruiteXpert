package com.tool.RecruitXpert.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long jobId;

    String jobRole; // web dev | ml | java dev

    String newJobRole; // whenever recruiter inserts new role

    String job_description;

    double experience;

    Long ctc_salary;

    String location;

    int vacancies;

    LocalDate last_date;

   // one recruiter many jobs
    @ManyToOne
    @JoinColumn
    private Admin admin;

    // one user many jobs
    @ManyToOne @JoinColumn
    private User userJobs;
}

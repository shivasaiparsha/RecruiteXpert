package com.tool.RecruitXpert.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor @Data @Table
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long jobid;

    String jobrole; // web dev | ml | java dev

    String newjobrole; // whenever recruiter inserts new role

    String jobdescription;

    double experience;

//    7LPA
    String ctc;

    String location;

    int vacancies;

    @CreationTimestamp
    Date lastDate;

   // one recruiter many jobs
    @ManyToOne
    @JoinColumn
    private Admin admin;

    // one user many jobs
    @ManyToOne @JoinColumn
    private User userjobs;
}

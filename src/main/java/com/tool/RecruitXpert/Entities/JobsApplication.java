package com.tool.RecruitXpert.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@NoArgsConstructor
@AllArgsConstructor @Data @Table
public class JobsApplication {
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

    // many-to-many to user - jobs application
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_jobs",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<User> usersApplied = new ArrayList<>();

    // i think i've to create an ManyToOne mapping for jobs - to - user here

}
